/*
 * ************************************************************************************************
 * * © 2021-2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */
package com.mymanu.companionapp.core.gaia.qtil.plugins

import com.mymanu.companionapp.core.publications.qtil.publishers.AudioCurationPublisher
import com.mymanu.companionapp.core.data.ACInfo

interface AudioCurationPlugin {
    val audioCurationPublisher: AudioCurationPublisher // to force the plugin to implement AudioCurationPublisher
    fun fetchInfo(info: ACInfo): Boolean
    fun fetchInfo(info: ACInfo, parameter: Any): Boolean
    fun setInfo(info: ACInfo, value: Any): Boolean
}
