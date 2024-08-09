/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.subscribers;

import androidx.annotation.NonNull;

import com.mymanu.companionapp.core.data.HandsetServiceInfo;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.publications.core.Subscriber;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;

public interface HandsetServiceSubscriber extends Subscriber {

    @NonNull
    default Subscription getSubscription() {
        return CoreSubscription.HANDSET_SERVICE;
    }

    void onInfo(HandsetServiceInfo info, Object update);

    void onError(HandsetServiceInfo info, Reason reason);

}
