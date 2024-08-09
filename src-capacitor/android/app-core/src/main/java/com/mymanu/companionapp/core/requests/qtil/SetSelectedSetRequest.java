/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.gaia.qtil.data.PreSet;
import com.mymanu.companionapp.core.gaia.qtil.plugins.MusicProcessingPlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.GaiaClientService;

public class SetSelectedSetRequest extends Request<Void, Void, Void> {

    private final PreSet set;

    public SetSelectedSetRequest(PreSet set) {
        super(null);
        this.set = set;
    }

    @Override
    public void run(@Nullable Context context) {
        MusicProcessingPlugin plugin = GaiaClientService.getQtilManager().getMusicProcessingPlugin();
        if (plugin != null) {
            plugin.selectSet(set);
        }
        onComplete(null);
    }

    @Override
    protected void onEnd() {
        // no state to clear
    }
}
