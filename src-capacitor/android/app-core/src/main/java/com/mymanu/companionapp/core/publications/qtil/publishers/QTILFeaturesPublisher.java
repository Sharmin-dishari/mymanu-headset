/*
 * ************************************************************************************************
 * * © 2020-2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.publishers;

import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.data.QTILFeature;
import com.mymanu.companionapp.core.publications.core.Publisher;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;
import com.mymanu.companionapp.core.publications.qtil.subscribers.QTILFeaturesSubscriber;

import androidx.annotation.NonNull;

public class QTILFeaturesPublisher extends Publisher<QTILFeaturesSubscriber> {

    @Override
    public Subscription getSubscription() {
        return CoreSubscription.FEATURES;
    }

    public void publishFeatureSupported(@NonNull QTILFeature feature, int version) {
        forEachSubscriber(subscriber -> subscriber.onFeatureSupported(feature, version));
    }

    public void publishFeatureNotSupported(@NonNull QTILFeature feature, Reason reason) {
        forEachSubscriber(subscriber -> subscriber.onFeatureNotSupported(feature, reason));
    }

    public void publishError(Reason reason) {
        forEachSubscriber(subscriber -> subscriber.onError(reason));
    }

}
