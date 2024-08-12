/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.data.ACInfo;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.plugins.AudioCurationPlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.requests.core.RequestListener;
import com.mymanu.companionapp.core.GaiaClientService;

public class SetAudioCurationRequest extends Request<Void, Void, Reason> {

    private final ACInfo info;

    private final Object value;

    public SetAudioCurationRequest(RequestListener<Void, Void, Reason> listener,
                                   ACInfo info, Object value) {
        super(listener);
        this.info = info;
        this.value = value;
    }

    @Override
    public void run(@Nullable Context context) {
        AudioCurationPlugin plugin = GaiaClientService.getQtilManager().getAudioCurationPlugin();
        if (plugin != null) {
            plugin.setInfo(info, value);
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
