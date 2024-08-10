/*
 * ************************************************************************************************
 * * © 2020 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.publishers;

import com.mymanu.companionapp.core.data.DeviceInfo;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.publications.core.Publisher;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;
import com.mymanu.companionapp.core.publications.qtil.subscribers.DeviceInformationSubscriber;

import androidx.annotation.NonNull;

public class DeviceInformationPublisher extends Publisher<DeviceInformationSubscriber> {

    @Override
    public Subscription getSubscription() {
        return CoreSubscription.DEVICE_INFORMATION;
    }

    public void publishInfo(@NonNull DeviceInfo info, @NonNull Object update) {
        forEachSubscriber(subscriber -> subscriber.onInfo(info, update));
    }

    public void publishError(@NonNull DeviceInfo info, Reason reason) {
        forEachSubscriber(subscriber -> subscriber.onError(info, reason));
    }

}
