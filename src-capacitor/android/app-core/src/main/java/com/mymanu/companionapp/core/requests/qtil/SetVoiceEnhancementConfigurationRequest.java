/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.data.VoiceEnhancementConfiguration;
import com.mymanu.companionapp.core.gaia.qtil.plugins.VoiceProcessingPlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.GaiaClientService;

public class SetVoiceEnhancementConfigurationRequest extends Request<Void, Void, Reason> {

    private final VoiceEnhancementConfiguration configuration;

    public SetVoiceEnhancementConfigurationRequest(VoiceEnhancementConfiguration configuration) {
        super(null);
        this.configuration = configuration;
    }

    @Override
    public void run(@Nullable Context context) {
        VoiceProcessingPlugin plugin = GaiaClientService.getQtilManager().getVoiceProcessingPlugin();
        if (plugin != null) {
            plugin.setConfiguration(configuration);
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
