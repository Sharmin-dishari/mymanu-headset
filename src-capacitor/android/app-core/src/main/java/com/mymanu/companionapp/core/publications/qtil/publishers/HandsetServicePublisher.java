/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.publishers;

import com.mymanu.companionapp.core.data.HandsetServiceInfo;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.data.MultipointType;
import com.mymanu.companionapp.core.publications.core.Publisher;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;
import com.mymanu.companionapp.core.publications.qtil.subscribers.HandsetServiceSubscriber;

public class HandsetServicePublisher extends Publisher<HandsetServiceSubscriber> {

    @Override
    public Subscription getSubscription() {
        return CoreSubscription.HANDSET_SERVICE;
    }

    public void publishMultiPointType(MultipointType type) {
        forEachSubscriber(subscriber -> subscriber.onInfo(HandsetServiceInfo.MULTIPOINT_TYPE, type));
    }

    public void publishError(HandsetServiceInfo info, Reason reason) {
        forEachSubscriber(subscriber -> subscriber.onError(info, reason));
    }
}
