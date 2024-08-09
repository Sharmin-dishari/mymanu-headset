/*
 * ************************************************************************************************
 * * © 2020-2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.subscribers;

import androidx.annotation.NonNull;

import com.mymanu.companionapp.core.data.MusicProcessingInfo;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.publications.core.Subscriber;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;

public interface MusicProcessingSubscriber extends Subscriber {

    @NonNull
    default Subscription getSubscription() {
        return CoreSubscription.MUSIC_PROCESSING;
    }

    void onEQInfo(MusicProcessingInfo info, Object update);

    void onEQError(MusicProcessingInfo info, Reason reason);
}
