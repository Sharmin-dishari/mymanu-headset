/*
 * ************************************************************************************************
 * * © 2020-2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.publishers;

import com.mymanu.companionapp.core.gaia.qtil.data.HandoverInformation;
import com.mymanu.companionapp.core.publications.core.Publisher;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;
import com.mymanu.companionapp.core.publications.qtil.subscribers.HandoverSubscriber;

public class HandoverPublisher extends Publisher<HandoverSubscriber> {

    @Override
    public Subscription getSubscription() {
        return CoreSubscription.HANDOVER;
    }

    public void publishHandoverStart(HandoverInformation info) {
        forEachSubscriber(subscriber -> subscriber.onHandoverStart(info));
    }

}
