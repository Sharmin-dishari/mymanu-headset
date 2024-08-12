/*
 * ************************************************************************************************
 * * © 2024 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */
package com.mymanu.companionapp.repository.deviceinfo

import android.content.Context
import androidx.lifecycle.LiveData
import com.mymanu.companionapp.core.data.DeviceInfo
import com.mymanu.companionapp.core.data.EarbudInfo
import com.mymanu.companionapp.core.gaia.qtil.data.basic.CrossUpdateVersion
import com.mymanu.companionapp.core.gaia.qtil.data.basic.SystemInformation
import com.mymanu.companionapp.core.gaia.qtil.data.basic.userfeatures.UserFeatures

interface DeviceInformationRepository {
    fun init()
    fun reset()

    fun fetchDeviceInfo(context: Context?, info: DeviceInfo)
    fun fetchEarbudInfo(context: Context?, info: EarbudInfo)

    val versions: LiveData<Versions>
    val deviceInformation: LiveData<DeviceInformation>
    val userFeatures: LiveData<UserFeatures>
    val systemInformation: LiveData<List<SystemInformation>>
    val crossUpdateVersion: LiveData<CrossUpdateVersion>
}
