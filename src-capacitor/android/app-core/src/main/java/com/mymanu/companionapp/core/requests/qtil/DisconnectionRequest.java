/*
 * ************************************************************************************************
 * * © 2020-2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.bluetooth.TransportManager;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.GaiaClientService;

public class DisconnectionRequest extends Request<Void, Void, Void> {

    public DisconnectionRequest() {
        super(null);
    }

    @Override
    public void run(@Nullable Context context) {
        TransportManager transportManager = GaiaClientService.getTransportManager();
        transportManager.disconnect(true);
        onComplete(null);
    }

    @Override
    protected void onEnd() {
    }

}
