/*
 * ************************************************************************************************
 * * © 2020 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.subscribers;

import com.mymanu.companionapp.core.data.EarbudInfo;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.publications.core.Subscriber;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;

import androidx.annotation.NonNull;

public interface EarbudSubscriber extends Subscriber {

    @NonNull
    default Subscription getSubscription() {
        return CoreSubscription.EARBUD;
    }

    void onInfo(EarbudInfo info, Object update, boolean mustRefreshData);

    void onError(EarbudInfo info, Reason reason);

}
