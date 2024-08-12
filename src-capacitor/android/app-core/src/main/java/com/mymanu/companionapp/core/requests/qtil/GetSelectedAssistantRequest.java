/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.gaia.qtil.plugins.VoiceUIPlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.GaiaClientService;

public class GetSelectedAssistantRequest extends Request<Void, Void, Void> {

    public GetSelectedAssistantRequest() {
        super(null);
    }

    @Override
    public void run(@Nullable Context context) {
        VoiceUIPlugin plugin = GaiaClientService.getQtilManager().getVoiceUIPlugin();
        if (plugin != null) {
            plugin.fetchSelectedAssistant();
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
