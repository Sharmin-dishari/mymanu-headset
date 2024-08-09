/*
 * ************************************************************************************************
 * * © 2020-2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.gaia.qtil.plugins.DebugPlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.GaiaClientService;

public class CancelDeviceLogsRequest extends Request<Void, Void, Void> {

    public CancelDeviceLogsRequest() {
        super(null);
    }

    @Override
    public void run(@Nullable Context context) {
        DebugPlugin plugin = GaiaClientService.getQtilManager().getDebugPlugin();
        if (plugin != null) {
            plugin.cancelDownload();
        }
        onComplete(null);
    }

    @Override
    protected void onEnd() {
        // no state to clear
    }
}
