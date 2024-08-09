/*
 * ************************************************************************************************
 * * © 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.ui.settings.download

import android.content.Context
import com.mymanu.companionapp.ui.settings.common.data.CustomSettingData
import com.mymanu.companionapp.ui.settings.common.data.EditTextSettingData
import com.mymanu.companionapp.ui.settings.common.data.SettingData
import com.mymanu.companionapp.ui.settings.common.data.SettingsViewData

class DownloadSettingsViewData internal constructor(context: Context) : SettingsViewData<DownloadSettings>(context) {
    override fun getKeys(): Array<DownloadSettings> {
        return DownloadSettings.values()
    }

    override fun initSettingData(context: Context, key: DownloadSettings): SettingData {
        return when (key) {
            DownloadSettings.APPLICATION_VERSION -> SettingData()
            DownloadSettings.APPLICATION_BUILD_ID -> SettingData()
            DownloadSettings.HARDWARE_VERSION -> EditTextSettingData()
            DownloadSettings.CREATED_AFTER -> CustomSettingData()
            DownloadSettings.CONTINUE -> SettingData()
            DownloadSettings.INCLUDE_TAGS -> SettingData()
            DownloadSettings.EXCLUDE_TAGS -> SettingData()
        }
    }
}
