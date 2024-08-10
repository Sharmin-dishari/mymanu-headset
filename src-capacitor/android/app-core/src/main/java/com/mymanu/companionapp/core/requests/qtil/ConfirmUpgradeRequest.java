/*
 * ************************************************************************************************
 * * © 2020-2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.gaia.qtil.plugins.UpgradePlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.requests.core.RequestListener;
import com.mymanu.companionapp.core.upgrade.data.UpgradeConfirmation;
import com.mymanu.companionapp.core.GaiaClientService;
import com.mymanu.companionapp.libraries.upgrade.data.ConfirmationOptions;

public class ConfirmUpgradeRequest extends Request<Void, Void, Void> {

    private final UpgradeConfirmation mConfirmation;

    @NonNull
    private final ConfirmationOptions option;

    public ConfirmUpgradeRequest(UpgradeConfirmation confirmation, @NonNull ConfirmationOptions option,
                                 @NonNull RequestListener<Void, Void, Void> listener) {
        super(listener);
        this.mConfirmation = confirmation;
        this.option = option;
    }

    @Override
    public void run(@Nullable Context context) {
        UpgradePlugin plugin = GaiaClientService.getQtilManager().getUpgradePlugin();
        if (plugin != null) {
            plugin.confirm(mConfirmation, option);
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
