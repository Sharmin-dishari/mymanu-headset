/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.gaia.qtil.plugins;

import com.mymanu.companionapp.core.data.HandsetServiceInfo;
import com.mymanu.companionapp.core.publications.qtil.publishers.HandsetServicePublisher;

public interface HandsetServicePlugin {

    // to force the HandsetServicePlugin to implement HandsetServicePublisher
    // this is unused
    HandsetServicePublisher getHandsetServicePublisher();

    void setInfo(HandsetServiceInfo info, Object value);
}
