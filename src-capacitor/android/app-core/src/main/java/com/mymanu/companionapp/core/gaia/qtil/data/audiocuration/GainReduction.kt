/*
 * ************************************************************************************************
 * * © 2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.gaia.qtil.data.audiocuration

import com.mymanu.companionapp.core.utils.BytesUtils

data class GainReduction(val detectionOnLeft: Detection, val detectionOnRight: Detection) {
    constructor(data: ByteArray) : this(
        Detection.valueOf(BytesUtils.getUINT8(data, LEFT_DETECTION_OFFSET)),
        Detection.valueOf(BytesUtils.getUINT8(data, RIGHT_DETECTION_OFFSET))
    )

    companion object {
        private const val LEFT_DETECTION_OFFSET = 0
        private const val RIGHT_DETECTION_OFFSET = 1
    }
}
