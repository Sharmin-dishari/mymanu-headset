/*
 * ************************************************************************************************
 * * © 2020-2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.publishers;

import com.mymanu.companionapp.core.data.DebugInfo;
import com.mymanu.companionapp.core.data.DownloadLogsState;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.data.LogSize;
import com.mymanu.companionapp.core.publications.core.Publisher;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;
import com.mymanu.companionapp.core.publications.qtil.subscribers.DebugSubscriber;

public class DebugPublisher extends Publisher<DebugSubscriber> {

    @Override
    public Subscription getSubscription() {
        return CoreSubscription.DEBUG;
    }

    public void publishLogSize(LogSize size) {
        forEachSubscriber(subscriber -> subscriber.onLogSize(size));
    }

    public void publishDownloadProgress(DownloadLogsState state, double progress) {
        forEachSubscriber(subscriber -> subscriber.onDownloadProgress(state, progress));
    }

    public void publishError(DebugInfo info, Reason reason) {
        forEachSubscriber(subscriber -> subscriber.onError(info, reason));
    }
}
