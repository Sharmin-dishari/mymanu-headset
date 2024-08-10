/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.data;

import com.mymanu.companionapp.core.gaia.qtil.data.EarbudsFitResults;

public enum FitInfo {
    /**
     * Information of type {@link EarbudsFitResults FitState}.
     */
    FIT_STATE,
    /**
     * No type information: only used to dispatch errors.
     */
    FIT_TEST
}
