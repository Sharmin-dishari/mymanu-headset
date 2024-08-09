/*
 * ************************************************************************************************
 * * © 2021-2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.publishers

import com.mymanu.companionapp.core.data.Reason
import com.mymanu.companionapp.core.data.StatisticsInfo
import com.mymanu.companionapp.core.gaia.qtil.data.CategoryID
import com.mymanu.companionapp.core.gaia.qtil.data.StatisticValue
import com.mymanu.companionapp.core.publications.core.Publisher
import com.mymanu.companionapp.core.publications.core.Subscription
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription
import com.mymanu.companionapp.core.publications.qtil.subscribers.StatisticsSubscriber

class StatisticsPublisher : Publisher<StatisticsSubscriber>() {
    override fun getSubscription(): Subscription {
        return CoreSubscription.STATISTICS
    }

    fun publishCategories(categories: List<CategoryID>) {
        forEachSubscriber { subscriber -> subscriber.onCategories(categories) }
    }

    fun publishStatistics(statistics: Map<CategoryID, List<StatisticValue>>) {
        forEachSubscriber { subscriber -> subscriber.onStatistics(statistics) }
    }

    fun publishError(info: StatisticsInfo?, reason: Reason?) {
        forEachSubscriber { subscriber -> subscriber.onError(info, reason) }
    }
}
