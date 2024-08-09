/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.data.ACInfo;
import com.mymanu.companionapp.core.gaia.qtil.plugins.AudioCurationPlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.GaiaClientService;

public class GetACInfoRequest extends Request<Void, Void, Void> {

    @NonNull
    private final ACInfo info;

    @Nullable
    private final Object parameters;

    public GetACInfoRequest(@NonNull ACInfo info) {
        super(null);
        this.info = info;
        this.parameters = null;
    }

    public GetACInfoRequest(@NonNull ACInfo info, @NonNull Object parameters) {
        super(null);
        this.info = info;
        this.parameters = parameters;
    }

    @Override
    public void run(@Nullable Context context) {
        AudioCurationPlugin plugin = GaiaClientService.getQtilManager().getAudioCurationPlugin();
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
            onError(null);
        }
    }

    @Override
    protected void onEnd() {
    }
}
