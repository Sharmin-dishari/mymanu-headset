/*
 * ************************************************************************************************
 * * © 2020-2021, 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.  *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.ui.settings.main;

import android.content.Context;

import com.mymanu.companionapp.BuildConfig;
import com.mymanu.companionapp.repository.service.ServiceAvailabilityImpl;
import com.mymanu.companionapp.ui.settings.common.data.ListSettingData;
import com.mymanu.companionapp.ui.settings.common.data.SettingData;
import com.mymanu.companionapp.ui.settings.common.data.SettingsViewData;
import com.mymanu.companionapp.ui.settings.main.MainSettings;

public class MainSettingsViewData extends SettingsViewData<MainSettings> {

    MainSettingsViewData(Context context) {
        super(context);
    }

    @Override
    public MainSettings[] getKeys() {
        return MainSettings.values();
    }

    @Override
    protected SettingData initSettingData(Context context, MainSettings key) {
        switch (key) {
            case CHANGE_DEVICE:
                // visible
                SettingData disconnectData = new SettingData();
                disconnectData.setVisible(true);
                return disconnectData;

            case ANC:
            case AUDIO_CURATION:
            case MUSIC_PROCESSING:
            case UPGRADE:
            case LOGS:
            case EARBUD_FIT:
            case DEVICE_FEATURES:
            case VOICE_PROCESSING:
            case GESTURES:
            case HANDSET_SERVICE:
            case STATISTICS:
                // not visible
                SettingData settingData = new SettingData();
                settingData.setVisible(false);
                return settingData;

            case VOICE_UI:
                // not visible
                SettingData listData = new ListSettingData();
                listData.setVisible(false);
                return listData;

            case FEEDBACK:
                SettingData feedbackData = new SettingData();
                feedbackData.setVisible(ServiceAvailabilityImpl.INSTANCE.getExists());
                feedbackData.setEnabled(ServiceAvailabilityImpl.INSTANCE.isAvailable(context));
                return feedbackData;

            case GAIA_CLIENT_VERSION:
                SettingData versionData = new SettingData();
                versionData.setSummary(BuildConfig.VERSION_NAME);
                return versionData;

            default:
                // unexpected
                return new SettingData();
        }
    }
}
