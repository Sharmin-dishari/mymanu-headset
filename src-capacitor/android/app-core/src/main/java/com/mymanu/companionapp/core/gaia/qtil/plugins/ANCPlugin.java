/*
 * ************************************************************************************************
 * * © 2020-2021, 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.  *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.gaia.qtil.plugins;

import com.mymanu.companionapp.core.data.ANCInfo;
import com.mymanu.companionapp.core.publications.qtil.publishers.ANCPublisher;

public interface ANCPlugin {

    // to force the ANCPlugin to implement VoiceUIPublisher
    // this is unused
    ANCPublisher getANCPublisher();

    void fetchANCInfo(ANCInfo info);

    void setANCInfo(ANCInfo info, Object value);

}
