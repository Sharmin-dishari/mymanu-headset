/*
 * ************************************************************************************************
 * * © 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.ui.settings.voiceprocessing

import com.mymanu.companionapp.R
import com.mymanu.companionapp.ui.settings.common.Settings

enum class VoiceProcessingSettings(override val resourceId: Int) : Settings {
    CATEGORY_3MIC_CAPABILITY(R.string.settings_id_voice_processing_3mic_capability),
    OPERATION_MODE(R.string.settings_id_voice_processing_operation_mode),
    MICROPHONE_MODE(R.string.settings_id_voice_processing_microphone_mode),
    BYPASS_MODE(R.string.settings_id_voice_processing_bypass_mode);
}
