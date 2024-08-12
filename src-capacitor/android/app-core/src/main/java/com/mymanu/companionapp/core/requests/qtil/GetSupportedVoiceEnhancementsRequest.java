/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.plugins.VoiceProcessingPlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.GaiaClientService;

public class GetSupportedVoiceEnhancementsRequest extends Request<Void, Void, Reason> {

    public GetSupportedVoiceEnhancementsRequest() {
        super(null);
    }

    @Override
    public void run(@Nullable Context context) {
        VoiceProcessingPlugin plugin = GaiaClientService.getQtilManager().getVoiceProcessingPlugin();
        if (plugin != null) {
            plugin.getSupportedEnhancements();
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
