/*
 * ************************************************************************************************
 * * © 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.ui.settings.logs

import com.mymanu.companionapp.R
import com.mymanu.companionapp.ui.settings.common.Settings

enum class LogsSettings(override val resourceId: Int) : Settings {
    LOGS_SIZES(R.string.settings_id_logs_sizes),
    BUG_REPORT(R.string.settings_id_logs_bug_report),
    DEVICE_LOGS(R.string.settings_id_logs_device_logs);
}
