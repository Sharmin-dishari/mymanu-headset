/*
 * ************************************************************************************************
 * * © 2020-2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests;

import android.content.Context;

import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.GaiaClientService;

import androidx.annotation.NonNull;

final class RequestManagerImpl {

    void execute(Context context, @SuppressWarnings("rawtypes") @NonNull Request request) {
        // use executor to allow the caller to end its process
        GaiaClientService.getTaskManager().runInBackground(() -> request.run(context));
    }
}
