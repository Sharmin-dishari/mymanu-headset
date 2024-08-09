/*
 * ************************************************************************************************
 * * © 2021-2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.bluetooth.data.BluetoothStatus;
import com.mymanu.companionapp.core.bluetooth.data.DiscoveredDevice;
import com.mymanu.companionapp.core.bluetooth.discovery.ConnectedDevicesFetcher;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.requests.core.RequestListener;

import java.lang.ref.WeakReference;
import java.util.List;

public class GetConnectedDevicesRequest extends Request<List<DiscoveredDevice>, Void, BluetoothStatus> {

    private ConnectedDevicesFetcher fetcher;

    private WeakReference<Context> appContextRef = null;

    @ConnectedDevicesFetcher.Profile
    private final int profile;

    public GetConnectedDevicesRequest(@ConnectedDevicesFetcher.Profile int profile,
                                      @NonNull RequestListener<List<DiscoveredDevice>, Void, BluetoothStatus> listener) {
        super(listener);
        this.profile = profile;
    }

    @Override
    public void run(@Nullable Context context) {
        appContextRef = context != null ? new WeakReference<>(context.getApplicationContext()) : null;

        fetcher = new ConnectedDevicesFetcher(profile, this::onComplete);
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
        if (fetcher != null) {
            Context context = appContextRef != null ? appContextRef.get() : null;
            fetcher.release(context);
            fetcher = null;
        }
    }

}
