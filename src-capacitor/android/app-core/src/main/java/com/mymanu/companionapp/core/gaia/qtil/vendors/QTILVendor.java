/*
 * ************************************************************************************************
 * * © 2020 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.gaia.qtil.vendors;

import com.mymanu.companionapp.core.gaia.qtil.data.QTILFeature;
import com.mymanu.companionapp.core.gaia.core.Plugin;

import androidx.annotation.NonNull;

public interface QTILVendor {

    void release();

    Plugin getPlugin(@NonNull QTILFeature feature);

}
