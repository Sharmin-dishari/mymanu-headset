/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.gaia.qtil.data.FitTestState;
import com.mymanu.companionapp.core.gaia.qtil.plugins.EarbudFitPlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.GaiaClientService;

public class SetEarbudFitTestRequest extends Request<Void, Void, Void> {

    private final FitTestState state;

    public SetEarbudFitTestRequest(FitTestState state) {
        super(null);
        this.state = state;
    }

    @Override
    public void run(@Nullable Context context) {
        EarbudFitPlugin plugin = GaiaClientService.getQtilManager().getEarbudFitPlugin();
        if (plugin != null) {
            plugin.setFitTest(state);
        }

        onComplete(null);
    }

    @Override
    protected void onEnd() {
        // no state to clear
    }
}
