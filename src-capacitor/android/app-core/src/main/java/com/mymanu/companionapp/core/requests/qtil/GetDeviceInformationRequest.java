/*
 * ************************************************************************************************
 * * © 2020-2021, 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.  *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.data.DeviceInfo;
import com.mymanu.companionapp.core.gaia.qtil.plugins.BasicPlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.GaiaClientService;

public class GetDeviceInformationRequest extends Request<Void, Void, Void> {

    @NonNull
    private final DeviceInfo info;

    public GetDeviceInformationRequest(@NonNull DeviceInfo info) {
        super(null);
        this.info = info;
    }

    @Override
    public void run(@Nullable Context context) {
        BasicPlugin basicPlugin = GaiaClientService.getQtilManager().getBasicPlugin();
        if (basicPlugin != null) {
            basicPlugin.fetchDeviceInfo(info);
        }

        onComplete(null);
    }

    @Override
    protected void onEnd() {
    }
}
