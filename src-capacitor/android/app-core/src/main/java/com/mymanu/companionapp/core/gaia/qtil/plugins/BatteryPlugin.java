/*
 * ************************************************************************************************
 * * © 2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.gaia.qtil.plugins;

import com.mymanu.companionapp.core.gaia.qtil.data.battery.Battery;
import com.mymanu.companionapp.core.publications.qtil.publishers.BatteryPublisher;

import java.util.Set;

public interface BatteryPlugin {

    void fetchSupportedBatteries();

    void fetchBatteryLevels(Set<Battery> batteries);

    // to force the BatteryPlugin to implement BatteryPublisher
    // this is unused
    BatteryPublisher getBatteryPublisher();

}
