/*
 * ************************************************************************************************
 * * © 2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */
package com.mymanu.companionapp.core.gaia.qtil.data.battery

import com.mymanu.companionapp.core.utils.BytesUtils.getUINT8

class SupportedBatteries(data: ByteArray) {
    val supported: Set<Battery> = buildSet {
        for (i in data.indices) {
            val type = Battery.valueOf(getUINT8(data[i]))
            type?.let { battery -> add(battery) }
        }
    }

    override fun toString(): String {
        return "SupportedBatteries(supported=$supported)"
    }

}