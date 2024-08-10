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

public class ReconnectionRequest extends Request<Void, Void, Void> {

    public ReconnectionRequest() {
        super(null);
    }

    @Override
    public void run(@Nullable Context context) {
        TransportManager manager = GaiaClientService.getTransportManager();
        manager.reconnect();
        onComplete(null);
    }

    @Override
    protected void onEnd() {

    }

}
