/*
 * ************************************************************************************************
 * * © 2020-2021, 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.  *
 * ************************************************************************************************
 */
package com.mymanu.companionapp.core.gaia.qtil.plugins

import com.mymanu.companionapp.core.gaia.qtil.data.VoiceAssistant
import com.mymanu.companionapp.core.publications.qtil.publishers.VoiceUIPublisher

interface VoiceUIPlugin {
    fun fetchSupportedAssistants()
    fun fetchSelectedAssistant()
    fun setSelectedAssistant(assistant: VoiceAssistant)

    // to force the VoiceUIPlugin to implement VoiceUIPublisher
    // this is unused
    val voiceUIPublisher: VoiceUIPublisher
}
