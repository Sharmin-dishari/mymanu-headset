/*
 * ************************************************************************************************
 * * © 2021-2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */
package com.mymanu.companionapp.ui.settings.statistics.category

import androidx.recyclerview.widget.RecyclerView.ViewHolder as RecyclerViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.mymanu.companionapp.core.gaia.qtil.data.XpanTransport
import com.mymanu.companionapp.databinding.StatisticsStatisticItemBinding
import com.mymanu.companionapp.databinding.StatisticsStatisticTransportItemBinding
import com.mymanu.companionapp.ui.common.ListAdapterDataItemCallback
import com.mymanu.companionapp.ui.settings.statistics.definitions.StatisticsCategories
import com.mymanu.companionapp.ui.settings.statistics.definitions.StreamingStatistics

private const val DEFAULT_ITEM_TYPE = 0
private const val TRANSPORT_ITEM_TYPE = 1
private const val SHOW_TRANSPORT_BUTTONS = true

class StatisticsStatisticsAdapter(
    val category: Int?,
    private val xpanSupported: Boolean,
    private val onXpanToggle: () -> Unit,
    private val onXpanTransport: (XpanTransport) -> Unit,
    private val onChangeAPState: (Boolean) -> Unit,
) : ListAdapter<StatisticViewData, StatisticsStatisticsAdapter.StatisticViewHolder>(ListAdapterDataItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticViewHolder {
        val binding = when (viewType) {
            TRANSPORT_ITEM_TYPE -> StatisticsStatisticTransportItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            else -> StatisticsStatisticItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }
        return StatisticViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when {
            category == StatisticsCategories.STREAMING.identifier
                    && item.id == StreamingStatistics.TRANSPORT.identifier -> TRANSPORT_ITEM_TYPE
            else -> DEFAULT_ITEM_TYPE
        }
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).hashCode().toLong()
    }

    override fun onBindViewHolder(holder: StatisticViewHolder, position: Int) {
        val item: StatisticViewData = getItem(position)
        when (val binding = holder.binding) {
            is StatisticsStatisticTransportItemBinding -> {
                binding.data = item
                binding.showActions = xpanSupported
                binding.showDetailedActions = SHOW_TRANSPORT_BUTTONS
                binding.statisticToggleButton.setOnClickListener { onXpanToggle() }
                binding.statisticWifiButton.setOnClickListener { onXpanTransport(XpanTransport.P2P) }
                binding.statisticsLeaButton.setOnClickListener { onXpanTransport(XpanTransport.LEA) }
                binding.statisticsApButton.setOnClickListener { onXpanTransport(XpanTransport.XPAN_AP) }
                binding.statisticConnectApButton.setOnClickListener { onChangeAPState(true) }
                binding.statisticsDisconnectApButton.setOnClickListener { onChangeAPState(false) }
            }
            is StatisticsStatisticItemBinding -> binding.data = item
        }
    }

    class StatisticViewHolder(val binding: ViewDataBinding) : RecyclerViewHolder(binding.root)

    init {
        setHasStableIds(true)
    }
}
