/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.data.GestureConfigurationInfo;
import com.mymanu.companionapp.core.gaia.qtil.plugins.GestureConfigurationPlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.GaiaClientService;

public class GetGestureInfoRequest extends Request<Void, Void, Void> {

    private static final String TAG = "GetGestureInfoRequest";

    @NonNull
    private final GestureConfigurationInfo info;

    @Nullable
    private final Object parameters;

    public GetGestureInfoRequest(@NonNull GestureConfigurationInfo info) {
        super(null);
        this.info = info;
        this.parameters = null;
    }

    public GetGestureInfoRequest(@NonNull GestureConfigurationInfo info, @NonNull Object parameters) {
        super(null);
        this.info = info;
        this.parameters = parameters;
    }

    @Override
    public void run(@Nullable Context context) {
        GestureConfigurationPlugin plugin = GaiaClientService.getQtilManager().getGestureConfigurationPlugin();
        if (plugin != null) {
            if (parameters == null) {
                plugin.fetchInfo(info);
            }
            else {
                plugin.fetchInfo(info, parameters);
            }
            onComplete(null);
        }
        else {
            Log.w(TAG, "[run] no corresponding plugin");
            onError(null);
        }
    }

    @Override
    protected void onEnd() {
    }
}
