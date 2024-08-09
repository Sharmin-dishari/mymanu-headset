/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.data.MusicProcessingInfo;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.plugins.MusicProcessingPlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.requests.core.RequestListener;
import com.mymanu.companionapp.core.GaiaClientService;

public class GetMusicProcessingInfoRequest extends Request<Void, Void, Reason> {

    private final MusicProcessingInfo info;

    public GetMusicProcessingInfoRequest(MusicProcessingInfo info,
                                         RequestListener<Void, Void, Reason> listener) {
        super(listener);
        this.info = info;
    }

    @Override
    public void run(@Nullable Context context) {
        MusicProcessingPlugin plugin = GaiaClientService.getQtilManager().getMusicProcessingPlugin();
        if (plugin != null) {
            if (plugin.fetch(info)) {
                onComplete(null);
            }
        }
        onError(Reason.NOT_SUPPORTED);
    }

    @Override
    protected void onEnd() {
        // no state to clear
    }
}
