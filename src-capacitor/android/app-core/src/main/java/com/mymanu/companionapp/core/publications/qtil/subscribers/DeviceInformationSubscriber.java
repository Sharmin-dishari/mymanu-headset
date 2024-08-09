/*
 * ************************************************************************************************
 * * © 2020 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.subscribers;

import com.mymanu.companionapp.core.data.DeviceInfo;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.publications.core.Subscriber;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;

import androidx.annotation.NonNull;

public interface DeviceInformationSubscriber extends Subscriber {

    @NonNull
    default Subscription getSubscription() {
        return CoreSubscription.DEVICE_INFORMATION;
    }

    void onInfo(DeviceInfo info, Object update);

    void onError(DeviceInfo info, Reason reason);

}
