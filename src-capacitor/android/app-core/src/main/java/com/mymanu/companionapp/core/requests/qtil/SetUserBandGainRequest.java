/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.gaia.qtil.plugins.MusicProcessingPlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.GaiaClientService;

public class SetUserBandGainRequest extends Request<Void, Void, Void> {

    private final int bandStart;

    private final int bandEnd;

    private final double[] gains;

    public SetUserBandGainRequest(int band, double gain) {
        this(band, band, gain);
    }

    public SetUserBandGainRequest(int bandStart, int bandEnd, double gain) {
        super(null);
        this.bandStart = bandStart;
        this.bandEnd = bandEnd;

        int count = Math.max(bandEnd - bandStart + 1, 0);
        double[] gains = new double[count];

        for (int i = 0; i < count; i++) {
            gains[i] = gain;
        }

        this.gains = gains;
    }

    public SetUserBandGainRequest(int bandStart, int bandEnd, @NonNull double[] gains) {
        super(null);
        this.bandStart = bandStart;
        this.bandEnd = bandEnd;
        this.gains = gains;
    }

    @Override
    public void run(@Nullable Context context) {
        MusicProcessingPlugin plugin = GaiaClientService.getQtilManager().getMusicProcessingPlugin();
        if (plugin != null) {
            plugin.setUserSetGains(bandStart, bandEnd, gains);
        }
        onComplete(null);
    }

    @Override
    protected void onEnd() {
        // no state to clear
    }
}
