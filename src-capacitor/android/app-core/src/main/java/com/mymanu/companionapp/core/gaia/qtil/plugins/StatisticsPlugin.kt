/*
 * ************************************************************************************************
 * * © 2021-2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.gaia.qtil.plugins

import com.mymanu.companionapp.core.data.StatisticsInfo
import com.mymanu.companionapp.core.publications.qtil.publishers.StatisticsPublisher


interface StatisticsPlugin {
    // to force the StatisticsPlugin to implement StatisticsPublisher
    // this is unused
    fun getStatisticsPublisher(): StatisticsPublisher

    fun fetchInfo(info: StatisticsInfo, parameter: Any?): Boolean
}
