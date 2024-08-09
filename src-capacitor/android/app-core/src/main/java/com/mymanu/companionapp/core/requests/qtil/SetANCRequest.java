/*
 * ************************************************************************************************
 * * © 2020-2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.data.ANCInfo;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.plugins.ANCPlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.requests.core.RequestListener;
import com.mymanu.companionapp.core.GaiaClientService;

public class SetANCRequest extends Request<Void, Void, Reason> {

    private final ANCInfo info;

    private final Object value;

    public SetANCRequest(RequestListener<Void, Void, Reason> listener,
                         ANCInfo info, Object value) {
        super(listener);
        this.info = info;
        this.value = value;
    }

    @Override
    public void run(@Nullable Context context) {
        ANCPlugin plugin = GaiaClientService.getQtilManager().getANCPlugin();
        if (plugin != null) {
            plugin.setANCInfo(info, value);
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
