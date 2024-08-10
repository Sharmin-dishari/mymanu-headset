/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.gaia.qtil.plugins;

import com.mymanu.companionapp.core.gaia.qtil.data.FitTestState;
import com.mymanu.companionapp.core.publications.qtil.publishers.EarbudFitPublisher;

public interface EarbudFitPlugin {

    // to force the EarbudFitPlugin to implement EarbudFitPublisher
    // this is unused
    EarbudFitPublisher getEarbudFitPublisher();

    void setFitTest(FitTestState state);
}
