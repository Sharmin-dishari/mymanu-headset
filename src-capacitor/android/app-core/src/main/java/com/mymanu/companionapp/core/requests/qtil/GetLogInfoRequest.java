/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.data.LogInfo;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.plugins.DebugPlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.requests.core.RequestListener;
import com.mymanu.companionapp.core.GaiaClientService;

public class GetLogInfoRequest extends Request<Void, Void, Reason> {

    @NonNull
    private final LogInfo info;


    public GetLogInfoRequest(@Nullable RequestListener<Void, Void, Reason> listener, @NonNull LogInfo info) {
        super(listener);
        this.info = info;
    }

    @Override
    public void run(@Nullable Context context) {
        DebugPlugin plugin = GaiaClientService.getQtilManager().getDebugPlugin();
        if (plugin != null) {
            plugin.fetchLogInfo(info);
            onComplete(null);
        }
        else {
            onError(Reason.NOT_SUPPORTED);
        }
    }

    @Override
    protected void onEnd() {
    }
}
