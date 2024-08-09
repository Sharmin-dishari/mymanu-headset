/*
 * ************************************************************************************************
 * * © 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.repository.service

import android.content.Context

interface ServiceAvailability {
    val exists: Boolean
    fun isAvailable(context: Context?): Boolean
}
