/*
 * ************************************************************************************************
 * * © 2021-2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.subscribers

import com.mymanu.companionapp.core.data.Reason
import com.mymanu.companionapp.core.data.StatisticsInfo
import com.mymanu.companionapp.core.gaia.qtil.data.CategoryID
import com.mymanu.companionapp.core.gaia.qtil.data.StatisticValue
import com.mymanu.companionapp.core.publications.core.Subscriber
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription

interface StatisticsSubscriber : Subscriber {

    override fun getSubscription() = CoreSubscription.STATISTICS

    fun onCategories(categories: List<CategoryID>)

    fun onStatistics(statistics: Map<CategoryID, List<StatisticValue>>)

    fun onError(info: StatisticsInfo?, reason: Reason?)
}
