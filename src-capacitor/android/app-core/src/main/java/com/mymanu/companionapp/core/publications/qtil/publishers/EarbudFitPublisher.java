/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.publishers;

import com.mymanu.companionapp.core.data.FitInfo;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.data.EarbudsFitResults;
import com.mymanu.companionapp.core.publications.core.Publisher;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;
import com.mymanu.companionapp.core.publications.qtil.subscribers.EarbudFitSubscriber;

public class EarbudFitPublisher extends Publisher<EarbudFitSubscriber> {

    @Override
    public Subscription getSubscription() {
        return CoreSubscription.EARBUD_FIT;
    }

    public void publishFitResults(EarbudsFitResults results) {
        forEachSubscriber(subscriber -> subscriber.onFitResults(results));
    }

    public void publishError(FitInfo info, Reason reason) {
        forEachSubscriber(subscriber -> subscriber.onFitError(info, reason));
    }
}
