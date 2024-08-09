/*
 * ************************************************************************************************
 * * © 2020 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.gaia.qtil.plugins;

import com.mymanu.companionapp.core.data.EarbudInfo;
import com.mymanu.companionapp.core.publications.qtil.publishers.EarbudPublisher;

public interface EarbudPlugin {

    void fetchEarbudInfo(EarbudInfo info);

    // to force the EarbudPlugin to implement EarbudPublisher
    // this is unused
    EarbudPublisher getEarbudPublisher();

}
