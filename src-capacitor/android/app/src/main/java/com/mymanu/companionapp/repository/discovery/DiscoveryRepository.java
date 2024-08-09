/*
 * ************************************************************************************************
 * * © 2020-2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.repository.discovery;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.mymanu.companionapp.core.bluetooth.data.DiscoveredDevice;
import com.mymanu.companionapp.core.bluetooth.data.DiscoveryType;
import com.mymanu.companionapp.repository.Result;
import com.mymanu.companionapp.repository.connection.BluetoothReason;

import java.util.List;

public interface DiscoveryRepository {

    @NonNull
    LiveData<Result<List<DiscoveredDevice>, BluetoothReason>> getDevices(DiscoveryType type);

    void refreshDevices(Context context, DiscoveryType type);

}
