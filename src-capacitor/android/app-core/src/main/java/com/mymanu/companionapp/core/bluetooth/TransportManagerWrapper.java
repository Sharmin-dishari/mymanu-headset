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
import com.mymanu.companionapp.core.publications.PublicationManager;

public final class TransportManagerWrapper implements TransportManager {

    private final TransportManagerImpl mManager;

    public TransportManagerWrapper(@NonNull PublicationManager publicationManager) {
        mManager = new TransportManagerImpl(publicationManager);
    }

    public void release() {
        mManager.release();
    }

    @Override
    public void logBytes(boolean log) {
        mManager.logBytes(log);
    }

    @Override
    public Device getDevice() {
        return mManager.getDevice();
    }

    @Override
    public GaiaTransport getGaiaTransport() {
        return mManager.getGaiaTransport();
    }

    @Override
    public BluetoothStatus connect(@NonNull Context context, @NonNull String address,
                                   @NonNull BluetoothType type) {
        return mManager.connect(context, address, type);
    }

    @Override
    public BluetoothStatus reconnect() {
        return mManager.reconnect();
    }

    @Override
    public void disconnect(boolean hard) {
        mManager.disconnect(hard);
    }
}
