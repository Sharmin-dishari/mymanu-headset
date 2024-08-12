/*
 * ************************************************************************************************
 * * © 2021, 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.       *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.ui.settings.handsetservice;

import android.content.Context;

import com.mymanu.companionapp.ui.settings.common.data.SettingData;
import com.mymanu.companionapp.ui.settings.common.data.SettingsViewData;
import com.mymanu.companionapp.ui.settings.common.data.SwitchSettingData;
import com.mymanu.companionapp.ui.settings.handsetservice.HandsetServiceSettings;

public class HandsetServiceViewData extends SettingsViewData<HandsetServiceSettings> {

    HandsetServiceViewData(Context context) {
        super(context);
    }

    @Override
    public HandsetServiceSettings[] getKeys() {
        return HandsetServiceSettings.values();
    }

    @Override
    protected SettingData initSettingData(Context context, HandsetServiceSettings key) {
        switch (key) {
            case MULTIPOINT_ENABLE:
                // visible switch setting
                SettingData switchData = new SwitchSettingData();
                switchData.setVisible(true);
                return switchData;

            default:
                // unexpected
                return new SettingData();
        }
    }

}
