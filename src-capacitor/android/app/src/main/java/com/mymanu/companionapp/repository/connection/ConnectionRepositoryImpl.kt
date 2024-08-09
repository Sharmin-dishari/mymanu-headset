/*
 * ************************************************************************************************
 * * © 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */
package com.mymanu.companionapp.repository.connection

import com.mymanu.companionapp.core.bluetooth.data.Device as CoreDevice
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import com.mymanu.companionapp.core.GaiaClientService
import com.mymanu.companionapp.core.bluetooth.data.BluetoothStatus
import com.mymanu.companionapp.core.bluetooth.data.ConnectionState
import com.mymanu.companionapp.core.publications.qtil.subscribers.ConnectionSubscriber
import com.mymanu.companionapp.core.requests.core.RequestListener
import com.mymanu.companionapp.core.requests.qtil.ConnectionRequest
import com.mymanu.companionapp.core.requests.qtil.DisconnectionRequest
import com.mymanu.companionapp.core.requests.qtil.ReconnectionRequest
import com.mymanu.companionapp.repository.Result
import com.mymanu.companionapp.ui.common.StickyLiveData
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "ConnectionRepository"

@Singleton
class ConnectionRepositoryImpl @Inject constructor() : ConnectionRepositoryData() {

    private val handler = Handler(Looper.getMainLooper())

    private val connectionSubscriber: ConnectionSubscriber = object : ConnectionSubscriber {
        override fun onConnectionStateChanged(coreDevice: CoreDevice, state: ConnectionState) {
            val device: Device = device ?: return
            if (device.bluetoothAddress == coreDevice.bluetoothAddress) {
                device.state = state
                updateDevice(device)
            }
        }

        override fun onConnectionError(coreDevice: CoreDevice, status: BluetoothStatus) {
            val device: Device = device ?: return
            if (device.bluetoothAddress == coreDevice.bluetoothAddress) {
                onError(status)
            }
        }
    }

    override fun connect(context: Context, device: Device?): LiveData<Result<Device, BluetoothStatus>> {
        val result = StickyLiveData<Result<Device, BluetoothStatus>>()
        // delays execution to make sure the caller gets the LiveData before any result
        handler.post { executeConnection(context, device, result) }
        return result
    }

    override fun disconnect(context: Context) {
        val device: Device? = device
        if (device == null) {
            // nothing to disconnect
            Log.i(TAG, "[disconnect] no connected device")
            return
        }
        val requestManager = GaiaClientService.getRequestManager()
        handler.post { requestManager.execute(context.applicationContext, DisconnectionRequest()) }

        // resetting the app data
        super.disconnect(context)
    }

    override fun reconnect(context: Context) {
        val device: Device? = device
        if (device == null) {
            // nothing to disconnect
            Log.i(TAG, "[reconnect] no previously connected device")
            return
        }
        val requestManager = GaiaClientService.getRequestManager()
        handler.post { requestManager.execute(context.applicationContext, ReconnectionRequest()) }
    }

    private fun initMonitoring(device: Device) {
        val currentDevice: Device? = this.device
        if (device != currentDevice) {
            // monitor for new device
            updateDevice(device)
            val publicationManager = GaiaClientService.getPublicationManager()
            publicationManager.subscribe(connectionSubscriber)
        }
    }

    private fun executeConnection(
        context: Context,
        device: Device?,
        result: StickyLiveData<Result<Device, BluetoothStatus>>
    ) {
        if (device == null) {
            Log.w(TAG, "[connect] device is null")
            result.postValue(Result.error(BluetoothStatus.DEVICE_NOT_FOUND))
            return
        }

        // init main monitoring
        initMonitoring(device)

        // init request LiveData
        result.postValue(Result.inProgress(device))

        // init the request
        val requestManager = GaiaClientService.getRequestManager()
        requestManager.execute(
            context.applicationContext,
            ConnectionRequest(
                device.bluetoothAddress, device.bluetoothType,
                buildRequestListener(device, result)
            )
        )
    }

    private fun buildRequestListener(
        device: Device,
        deviceData: StickyLiveData<Result<Device, BluetoothStatus>>
    ): RequestListener<Void?, Void?, BluetoothStatus> {
        return object : RequestListener<Void?, Void?, BluetoothStatus> {
            override fun onProgress(empty: Void?) {
                device.state = ConnectionState.CONNECTING
                deviceData.postValue(Result.inProgress(device))
            }

            override fun onComplete(empty: Void?) {
                device.state = ConnectionState.CONNECTED
                deviceData.postValue(Result.success(device))
            }

            override fun onError(status: BluetoothStatus?) {
                if (status == BluetoothStatus.ALREADY_CONNECTED
                    || status == BluetoothStatus.IN_PROGRESS
                ) {
                    // unexpected status as they don't represent an error
                    Log.w(TAG, "[RequestListener->onError] unexpected connection status received: $status")
                    return
                }
                device.state = ConnectionState.DISCONNECTED
                deviceData.postValue(Result.error(device, status))
            }
        }
    }
}
