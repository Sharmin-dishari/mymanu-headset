/*
 * ************************************************************************************************
 * * © 2020-2021, 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.  *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.gaia.qtil.plugins.UpgradePlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.requests.core.RequestListener;
import com.mymanu.companionapp.core.upgrade.data.UpdateOptions;
import com.mymanu.companionapp.core.GaiaClientService;

public class StartUpgradeRequest extends Request<Void, Void, Void> {

    @NonNull
    private final UpdateOptions options;

    public StartUpgradeRequest(@NonNull UpdateOptions options, @NonNull RequestListener<Void, Void, Void> listener) {
        super(listener);
        this.options = options;
    }

    @Override
    public void run(@Nullable Context context) {
        UpgradePlugin plugin = GaiaClientService.getQtilManager().getUpgradePlugin();
        if (plugin != null) {
            plugin.startUpgrade(context, options);
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
