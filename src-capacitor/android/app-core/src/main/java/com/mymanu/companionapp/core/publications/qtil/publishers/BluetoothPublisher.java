/*
 * ************************************************************************************************
 * * © 2020 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.publishers;

import com.mymanu.companionapp.core.publications.core.Publisher;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;
import com.mymanu.companionapp.core.publications.qtil.subscribers.BluetoothSubscriber;

public class BluetoothPublisher extends Publisher<BluetoothSubscriber> {

    @Override
    public Subscription getSubscription() {
        return CoreSubscription.BLUETOOTH;
    }

    public void publishBluetoothEnabled() {
        forEachSubscriber(BluetoothSubscriber::onEnabled);
    }

    public void publishBluetoothDisabled() {
        forEachSubscriber(BluetoothSubscriber::onDisabled);
    }

}
