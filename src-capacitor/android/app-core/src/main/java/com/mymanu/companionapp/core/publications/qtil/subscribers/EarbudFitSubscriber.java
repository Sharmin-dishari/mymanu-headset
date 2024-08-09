/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.subscribers;

import androidx.annotation.NonNull;

import com.mymanu.companionapp.core.data.FitInfo;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.data.EarbudsFitResults;
import com.mymanu.companionapp.core.publications.core.Subscriber;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;

public interface EarbudFitSubscriber extends Subscriber {

    @NonNull
    default Subscription getSubscription() {
        return CoreSubscription.EARBUD_FIT;
    }

    void onFitResults(EarbudsFitResults results);

    void onFitError(FitInfo info, Reason reason);

}
