/*
 * ************************************************************************************************
 * * © 2021-2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.ui.settings.statistics.category

import com.mymanu.companionapp.ui.common.ListAdapterItemData

data class StatisticViewData(val title: String, val subtitle: String, val id: Int) : ListAdapterItemData {
    override fun isSameContent(itemData: ListAdapterItemData): Boolean {
        if (this === itemData) {
            return true
        }
        if (javaClass != itemData.javaClass) {
            return false
        }
        val that = itemData as StatisticViewData
        return that.title == title && that.subtitle == subtitle
    }

    override fun isSameItem(itemData: ListAdapterItemData): Boolean {
        return this == itemData
    }
}
