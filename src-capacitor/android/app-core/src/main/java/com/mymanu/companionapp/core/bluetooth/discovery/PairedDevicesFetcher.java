/*
 * ************************************************************************************************
 * * © 2020-2021, 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.  *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.bluetooth.discovery;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.mymanu.companionapp.core.bluetooth.data.BluetoothStatus;
import com.mymanu.companionapp.core.bluetooth.data.BluetoothType;
import com.mymanu.companionapp.core.bluetooth.data.DiscoveredDevice;
import com.mymanu.companionapp.core.bluetooth.data.DiscoveryType;
import com.mymanu.companionapp.core.utils.BluetoothUtils;
import com.mymanu.companionapp.core.GaiaClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PairedDevicesFetcher {

    private static final String TAG = "PairedDevicesFetcher";

    @NonNull
    private final PairedDevicesFetcherListener mListener;

    public PairedDevicesFetcher(@NonNull PairedDevicesFetcherListener listener) {
        this.mListener = listener;
    }

    public BluetoothStatus get(Context context) {
        // getting BluetoothAdapter to get resources
        BluetoothAdapter adapter = BluetoothUtils.getBluetoothAdapter(context);
        if (adapter == null) {
            // no BluetoothAdapter = Bluetooth is off or not available for the device
            Log.w(TAG, "[get] BluetoothAdapter is null.");
            return BluetoothStatus.NO_BLUETOOTH;
        }

        // use a handler to make this process ends prior to find any UUIDs
        GaiaClientService.getTaskManager().runInBackground(() -> findPairedDevices(adapter));

        return BluetoothStatus.IN_PROGRESS;
    }

    private void findPairedDevices(@NonNull BluetoothAdapter adapter) {
        @SuppressLint("MissingPermission") Set<BluetoothDevice> paired = adapter.getBondedDevices();
        mListener.onGetPairedDevices(getPairedDevices(paired));
    }

    @SuppressLint("MissingPermission")
    private List<DiscoveredDevice> getPairedDevices(Set<BluetoothDevice> paired) {
        if (paired == null || paired.isEmpty()) {
            return new ArrayList<>();
        }

        List<DiscoveredDevice> result = new ArrayList<>();

        paired.forEach(device -> {
            switch (device.getType()) {
                case BluetoothDevice.DEVICE_TYPE_CLASSIC:
                    result.add(new DiscoveredDevice(device, DiscoveryType.PAIRED, BluetoothType.CLASSIC));
                    break;
                case BluetoothDevice.DEVICE_TYPE_LE:
                    result.add(new DiscoveredDevice(device, DiscoveryType.PAIRED, BluetoothType.LOW_ENERGY));
                    break;
                case BluetoothDevice.DEVICE_TYPE_DUAL:
                    result.add(new DiscoveredDevice(device, DiscoveryType.PAIRED, BluetoothType.LOW_ENERGY));
                    result.add(new DiscoveredDevice(device, DiscoveryType.PAIRED, BluetoothType.CLASSIC));
                    break;
            }
        });

        return result;
    }

    public interface PairedDevicesFetcherListener {

        void onGetPairedDevices(List<DiscoveredDevice> pairedDevices);

    }
}
