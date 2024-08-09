/*
 * ************************************************************************************************
 * * © 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.repository.deviceinfo

import com.mymanu.companionapp.core.gaia.qtil.data.basic.SystemInformation

object InfoUtils {
    fun readApplicationBuildId(repository: DeviceInformationRepository): String? {
        val infos = repository.systemInformation.value
        val info: SystemInformation? = infos?.find { it is SystemInformation.ApplicationBuildId }
        return (info as? SystemInformation.ApplicationBuildId)?.value?.text
    }

    fun readApplicationVersion(repository: DeviceInformationRepository): String? {
        return repository.versions.value?.application
    }
}
