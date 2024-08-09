/*
 * ************************************************************************************************
 * * © 2020-2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.subscribers;

import androidx.annotation.NonNull;

import com.mymanu.companionapp.core.data.DebugInfo;
import com.mymanu.companionapp.core.data.DownloadLogsState;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.data.LogSize;
import com.mymanu.companionapp.core.publications.core.Subscriber;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;

public interface DebugSubscriber extends Subscriber {

    @NonNull
    default Subscription getSubscription() {
        return CoreSubscription.DEBUG;
    }

    void onLogSize(LogSize size);

    void onDownloadProgress(DownloadLogsState state, double progress);

    void onError(DebugInfo info, Reason reason);

}
