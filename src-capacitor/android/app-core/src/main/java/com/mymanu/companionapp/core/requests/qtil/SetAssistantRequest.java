/*
 * ************************************************************************************************
 * * © 2020-2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.data.VoiceAssistant;
import com.mymanu.companionapp.core.gaia.qtil.plugins.VoiceUIPlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.requests.core.RequestListener;
import com.mymanu.companionapp.core.GaiaClientService;

public class SetAssistantRequest extends Request<Void, Void, Reason> {

    @NonNull
    private final VoiceAssistant assistant;

    public SetAssistantRequest(@NonNull VoiceAssistant assistant,
                               RequestListener<Void, Void, Reason> listener) {
        super(listener);
        this.assistant = assistant;
    }

    @Override
    public void run(@Nullable Context context) {
        VoiceUIPlugin plugin = GaiaClientService.getQtilManager().getVoiceUIPlugin();
        if (plugin != null) {
            plugin.setSelectedAssistant(assistant);
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
