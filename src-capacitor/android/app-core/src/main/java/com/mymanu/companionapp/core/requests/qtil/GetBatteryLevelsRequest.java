/*
 * ************************************************************************************************
 * * © 2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.gaia.qtil.data.battery.Battery;
import com.mymanu.companionapp.core.gaia.qtil.plugins.BatteryPlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.GaiaClientService;

import java.util.Set;

public class GetBatteryLevelsRequest extends Request<Void, Void, Void> {

    private final Set<Battery> batteries;

    public GetBatteryLevelsRequest(Set<Battery> batteries) {
        super(null);
        this.batteries = batteries;
    }

    @Override
    public void run(@Nullable Context context) {
        BatteryPlugin plugin = GaiaClientService.getQtilManager().getBatteryPlugin();
        if (plugin != null) {
            plugin.fetchBatteryLevels(batteries);
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
