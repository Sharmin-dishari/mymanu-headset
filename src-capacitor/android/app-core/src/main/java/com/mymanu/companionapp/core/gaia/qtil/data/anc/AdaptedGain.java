/*
 * ************************************************************************************************
 * * © 2020-2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.gaia.qtil.data.anc;

import com.mymanu.companionapp.core.gaia.qtil.data.EarbudPosition;

public class AdaptedGain {

    private final EarbudPosition position;

    private final Gain gain;

    public AdaptedGain(EarbudPosition position, Gain gain) {
        this.position = position;
        this.gain = gain;
    }

    public EarbudPosition getPosition() {
        return position;
    }

    public Gain getGain() {
        return gain;
    }
}
