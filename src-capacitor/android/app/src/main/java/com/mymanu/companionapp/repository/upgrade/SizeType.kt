/*
 * ************************************************************************************************
 * * © 2021, 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.       *
 * ************************************************************************************************
 */
package com.mymanu.companionapp.repository.upgrade

import com.mymanu.companionapp.core.gaia.core.GaiaPdu
import com.mymanu.companionapp.libraries.upgrade.messages.OpCodes.UpgradeData
import com.mymanu.companionapp.libraries.upgrade.messages.UpgradeMessage

enum class SizeType {
    EXPECTED,
    SET,
    MAX,
    DEFAULT;

    val default: Int
        get() = if (this@SizeType != EXPECTED) {
            GaiaPdu.Payload.minLength - UpgradeMessage.HEADER_LENGTH - UpgradeData.DATA_HEADER_LENGTH
        } else NULL_VALUE

    companion object {
        const val NULL_VALUE = -1
    }
}
