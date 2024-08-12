/*
 * ************************************************************************************************
 * * © 2020, 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.       *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.bluetooth;

import android.content.Context;

import androidx.annotation.NonNull;

import com.mymanu.companionapp.core.bluetooth.data.BluetoothStatus;
import com.mymanu.companionapp.core.bluetooth.data.BluetoothType;
import com.mymanu.companionapp.core.bluetooth.data.Device;

public interface TransportManager {

    BluetoothStatus connect(@NonNull Context context, @NonNull String address, @NonNull BluetoothType type);

    BluetoothStatus reconnect();

    void disconnect(boolean hard);

    GaiaTransport getGaiaTransport();

    void logBytes(boolean log);

    Device getDevice();
}
