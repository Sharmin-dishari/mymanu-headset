/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.data.ANCInfo;
import com.mymanu.companionapp.core.gaia.qtil.plugins.ANCPlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.GaiaClientService;

public class GetANCInfoRequest extends Request<Void, Void, Void> {

    @NonNull
    private final ANCInfo info;

    public GetANCInfoRequest(@NonNull ANCInfo info) {
        super(null);
        this.info = info;
    }

    @Override
    public void run(@Nullable Context context) {
        ANCPlugin plugin = GaiaClientService.getQtilManager().getANCPlugin();
        if (plugin != null) {
            plugin.fetchANCInfo(info);
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
