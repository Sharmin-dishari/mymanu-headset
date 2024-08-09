/*
 * ************************************************************************************************
 * * © 2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.repository.battery;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.mymanu.companionapp.core.gaia.qtil.data.battery.Battery;

import java.util.Map;
import java.util.Set;

public interface BatteryRepository {

    void init();

    void fetchSupportedBatteries(Context context);

    void fetchBatteryLevels(Context context, Set<Battery> batteries);

    @NonNull
    LiveData<Set<Battery>> getSupportedBatteriesLiveData();

    @NonNull
    LiveData<Map<Battery, Integer>> getBatteryLevelsLiveData();

    void reset();
}
