/*
 * ************************************************************************************************
 * * © 2021-2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.ui.settings.statistics.category

data class StatisticsStatisticsViewData(
    val isRecording: Boolean = false,
    val statistics: List<StatisticViewData> = emptyList(),
)
