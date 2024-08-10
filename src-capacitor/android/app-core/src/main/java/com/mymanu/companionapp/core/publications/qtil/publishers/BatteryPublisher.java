/*
 * ************************************************************************************************
 * * © 2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.publishers;

import com.mymanu.companionapp.core.gaia.qtil.data.battery.Battery;
import com.mymanu.companionapp.core.gaia.qtil.data.battery.BatteryLevel;
import com.mymanu.companionapp.core.publications.core.Publisher;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;
import com.mymanu.companionapp.core.publications.qtil.subscribers.BatterySubscriber;

import java.util.Set;

public class BatteryPublisher extends Publisher<BatterySubscriber> {

    @Override
    public Subscription getSubscription() {
        return CoreSubscription.BATTERY;
    }

    public void publishSupportedBatteries(Set<Battery> supported) {
        forEachSubscriber(subscriber -> subscriber.onSupportedBatteries(supported));
    }

    public void publishBatteryLevels(Set<BatteryLevel> levels) {
        forEachSubscriber(subscriber -> subscriber.onBatteryLevels(levels));
    }

}
