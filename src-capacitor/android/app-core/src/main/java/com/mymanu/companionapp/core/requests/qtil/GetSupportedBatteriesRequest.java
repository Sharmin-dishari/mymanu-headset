/*
 * ************************************************************************************************
 * * © 2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.gaia.qtil.plugins.BatteryPlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.GaiaClientService;

public class GetSupportedBatteriesRequest extends Request<Void, Void, Void> {

    public GetSupportedBatteriesRequest() {
        super(null);
    }

    @Override
    public void run(@Nullable Context context) {
        BatteryPlugin plugin = GaiaClientService.getQtilManager().getBatteryPlugin();
        if (plugin != null) {
            plugin.fetchSupportedBatteries();
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
