/*
 * ************************************************************************************************
 * * © 2020-2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.repository.connection;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.mymanu.companionapp.core.bluetooth.data.BluetoothStatus;
import com.mymanu.companionapp.repository.Resource;
import com.mymanu.companionapp.repository.Result;

public interface ConnectionRepository {

    @NonNull
    LiveData<Resource<Device, BluetoothStatus>> getConnectedDeviceLiveData();

    /**
     * <p>Initiates the main connection to the given device and start monitoring related
     * connection states.</p>
     */
    LiveData<Result<Device, BluetoothStatus>> connect(@NonNull Context context, Device device);

    void disconnect(@NonNull Context context);

    void reconnect(@NonNull Context context);

    @Nullable
    default Device getDevice() {
        Resource<Device, BluetoothStatus> resource = getConnectedDeviceLiveData().getValue();
        return resource != null ? resource.getData() : null;
    }
}