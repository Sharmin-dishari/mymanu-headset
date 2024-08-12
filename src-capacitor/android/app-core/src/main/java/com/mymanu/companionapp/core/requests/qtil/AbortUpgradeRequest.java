/*
 * ************************************************************************************************
 * * © 2020-2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.upgrade.UpgradeHelper;
import com.mymanu.companionapp.core.GaiaClientService;

public class AbortUpgradeRequest extends Request<Void, Void, Void> {

    public AbortUpgradeRequest() {
        super(null);
    }

    @Override
    public void run(@Nullable Context context) {
        // getting the UpgradeHelper to force the abort even when a device is disconnected
        UpgradeHelper helper = GaiaClientService.getQtilManager().getUpgradeHelper();
        helper.abort();
        onComplete(null);
    }

    @Override
    protected void onEnd() {
    }

}
