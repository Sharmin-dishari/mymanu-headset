/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.data.HandsetServiceInfo;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.plugins.HandsetServicePlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.GaiaClientService;

public class SetHandsetServiceRequest extends Request<Void, Void, Reason> {

    private final HandsetServiceInfo info;

    private final Object value;

    public SetHandsetServiceRequest(HandsetServiceInfo info, Object value) {
        super(null);
        this.info = info;
        this.value = value;
    }

    @Override
    public void run(@Nullable Context context) {
        HandsetServicePlugin plugin = GaiaClientService.getQtilManager().getHandsetServicePlugin();
        if (plugin != null) {
            plugin.setInfo(info, value);
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
