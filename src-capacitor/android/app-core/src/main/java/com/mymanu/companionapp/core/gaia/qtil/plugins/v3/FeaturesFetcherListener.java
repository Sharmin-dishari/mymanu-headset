/*
 * ************************************************************************************************
 * * © 2020-2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.gaia.qtil.plugins.v3;

import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.data.SupportedFeatures;

public interface FeaturesFetcherListener {

    void onSupported(SupportedFeatures features);

    void onError(Reason reason);

}
