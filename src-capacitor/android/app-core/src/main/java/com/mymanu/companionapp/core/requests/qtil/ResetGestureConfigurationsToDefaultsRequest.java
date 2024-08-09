/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.gaia.qtil.plugins.GestureConfigurationPlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.GaiaClientService;

public class ResetGestureConfigurationsToDefaultsRequest extends Request<Void, Void, Void> {

    public ResetGestureConfigurationsToDefaultsRequest() {
        super(null);
    }

    @Override
    public void run(@Nullable Context context) {
        GestureConfigurationPlugin plugin = GaiaClientService.getQtilManager().getGestureConfigurationPlugin();
        if (plugin != null) {
            plugin.resetToDefault();
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
