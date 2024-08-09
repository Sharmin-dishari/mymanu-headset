/*
 * ************************************************************************************************
 * * © 2024 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.ui.information.dialog

import android.content.Context
import com.mymanu.companionapp.R
import com.mymanu.companionapp.ui.common.progress.DialogOption

/**
 *
 * All the options that can apply for the confirmations used in the device info screen.
 */

enum class DeviceInfoDialogOption(private val labelId: Int, private val option: Options): DialogOption<Options> {
    OKAY(R.string.button_ok, Options.OKAY);

    override fun getIdentifier(): Options {
        return option
    }

    override fun getLabel(context: Context): String {
        return context.getString(labelId)
    }
}

enum class Options(val value: Int) {
    OKAY(0)
}
