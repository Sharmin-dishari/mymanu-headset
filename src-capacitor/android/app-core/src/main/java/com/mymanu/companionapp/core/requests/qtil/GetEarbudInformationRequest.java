/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.data.EarbudInfo;
import com.mymanu.companionapp.core.gaia.qtil.plugins.EarbudPlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.GaiaClientService;

public class GetEarbudInformationRequest extends Request<Void, Void, Void> {

    @NonNull
    private final EarbudInfo info;

    public GetEarbudInformationRequest(@NonNull EarbudInfo info) {
        super(null);
        this.info = info;
    }

    @Override
    public void run(@Nullable Context context) {
        EarbudPlugin earbudPlugin = GaiaClientService.getQtilManager().getEarbudPlugin();
        if (earbudPlugin != null) {
            earbudPlugin.fetchEarbudInfo(info);
        }

        onComplete(null);
    }

    @Override
    protected void onEnd() {
    }
}
