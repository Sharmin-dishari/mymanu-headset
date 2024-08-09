/*
 * ************************************************************************************************
 * * © 2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.subscribers;

import androidx.annotation.NonNull;

import com.mymanu.companionapp.core.gaia.qtil.data.battery.Battery;
import com.mymanu.companionapp.core.gaia.qtil.data.battery.BatteryLevel;
import com.mymanu.companionapp.core.publications.core.Subscriber;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;

import java.util.Set;

public interface BatterySubscriber extends Subscriber {

    @NonNull
    default Subscription getSubscription() {
        return CoreSubscription.BATTERY;
    }

    void onSupportedBatteries(Set<Battery> supported);

    void onBatteryLevels(Set<BatteryLevel> levels);

}
