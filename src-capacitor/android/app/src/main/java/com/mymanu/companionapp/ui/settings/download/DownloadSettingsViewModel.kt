/*
 * ************************************************************************************************
 * * © 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.ui.settings.download

import android.app.Application
import android.content.Context
import com.mymanu.companionapp.R
import com.mymanu.companionapp.core.bluetooth.data.ConnectionState
import com.mymanu.companionapp.core.data.DeviceInfo
import com.mymanu.companionapp.core.gaia.qtil.data.basic.SystemInformation
import com.mymanu.companionapp.core.gaia.qtil.data.basic.SystemInformation.ApplicationBuildId
import com.mymanu.companionapp.repository.connection.ConnectionRepository
import com.mymanu.companionapp.repository.deviceinfo.DeviceInformationRepository
import com.mymanu.companionapp.repository.deviceinfo.Versions
import com.mymanu.companionapp.ui.common.Observers
import com.mymanu.companionapp.ui.settings.common.SettingsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DownloadSettingsViewModel @Inject constructor(
    application: Application,
    connectionRepository: ConnectionRepository,
    private val deviceInfoRepository: DeviceInformationRepository
) : SettingsViewModel<DownloadSettings, DownloadSettingsViewData>(application, connectionRepository) {

    private val observers = DataObservers()

    init {
        observers.start()
    }

    override fun onCleared() {
        super.onCleared()
        observers.stop()
    }

    override fun initViewData(context: Context): DownloadSettingsViewData {
        return DownloadSettingsViewData(context)
    }

    override fun onConnectionStateUpdated(state: ConnectionState?) {
        val connected = state == ConnectionState.CONNECTED
        setSettingEnable(DownloadSettings.CONTINUE, connected)

        if (state == ConnectionState.CONNECTED) {
            fetchDeviceInformation()
        }
    }

    private fun fetchDeviceInformation() {
        deviceInfoRepository.fetchDeviceInfo(getApplication(), DeviceInfo.APPLICATION_VERSION)
        deviceInfoRepository.fetchDeviceInfo(getApplication(), DeviceInfo.SYSTEM_INFORMATION)
    }

    private fun onVersions(versions: Versions?) {
        val label = versions?.application ?: getApplication<Application>().getString(R.string.info_unknown)
        setSettingSummary(DownloadSettings.APPLICATION_VERSION, label)
    }

    private fun onSystemInformation(infos: List<SystemInformation>?) {
        infos?.forEach {
            when (it) {
                is ApplicationBuildId -> updateApplicationBuildId(it)
                else -> {}
            }
        }
    }

    private fun updateApplicationBuildId(id: ApplicationBuildId) {
        val label = id.value.text ?: getApplication<Application>().getString(R.string.info_unknown)
        setSettingSummary(DownloadSettings.APPLICATION_BUILD_ID, label)
    }

    private inner class DataObservers : Observers() {
        private val versionsObserver = this@DownloadSettingsViewModel::onVersions
        private val systemInformationObserver = this@DownloadSettingsViewModel::onSystemInformation

        override fun registerObservers() {
            deviceInfoRepository.versions.observeForever(versionsObserver)
            deviceInfoRepository.systemInformation.observeForever(systemInformationObserver)
        }

        override fun unregisterObservers() {
            deviceInfoRepository.versions.removeObserver(versionsObserver)
            deviceInfoRepository.systemInformation.removeObserver(systemInformationObserver)
        }
    }
}
