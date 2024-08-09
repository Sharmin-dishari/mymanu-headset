/*
 * ************************************************************************************************
 * * © 2024 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.gaia.qtil.data.basic

data class CrossUpdateVersion(
    val required: Boolean,
    val major: Int,
    val minor: Int,
    val ps: Int
)
