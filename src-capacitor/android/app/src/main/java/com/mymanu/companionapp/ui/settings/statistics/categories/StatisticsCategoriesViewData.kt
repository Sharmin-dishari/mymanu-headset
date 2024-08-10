/*
 * ************************************************************************************************
 * * © 2021-2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.ui.settings.statistics.categories

import com.mymanu.companionapp.repository.statistics.StatisticsCategoriesCategoryInfo

data class StatisticsCategoriesViewData(
    val isRecording: Boolean,
    val categoriesInfo: List<StatisticsCategoriesCategoryInfo>
)
