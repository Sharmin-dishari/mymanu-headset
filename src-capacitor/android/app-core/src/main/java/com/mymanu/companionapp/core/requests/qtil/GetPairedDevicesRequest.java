/*
 * ************************************************************************************************
 * * © 2020-2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.bluetooth.data.BluetoothStatus;
import com.mymanu.companionapp.core.bluetooth.data.DiscoveredDevice;
import com.mymanu.companionapp.core.bluetooth.discovery.PairedDevicesFetcher;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.requests.core.RequestListener;

import java.util.List;

public class GetPairedDevicesRequest extends Request<List<DiscoveredDevice>, Void,
        BluetoothStatus> {

    public GetPairedDevicesRequest(
            @NonNull RequestListener<List<DiscoveredDevice>, Void, BluetoothStatus> listener) {
        super(listener);
    }

    @Override
    public void run(@Nullable Context context) {
        PairedDevicesFetcher fetcher = new PairedDevicesFetcher(this::onComplete);
        BluetoothStatus status = fetcher.get(context);

        // check returned status
        if (status != BluetoothStatus.IN_PROGRESS) {
            onError(status);
        }
        else {
            onProgress(null);
        }
    }

    @Override
    protected void onEnd() {
    }

}
