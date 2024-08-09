/*
 * ************************************************************************************************
 * * © 2024 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.data;

import com.mymanu.companionapp.core.gaia.core.version.GaiaVersion;
import com.mymanu.companionapp.core.gaia.qtil.data.basic.ChargerStatus;
import com.mymanu.companionapp.core.gaia.qtil.data.basic.CrossUpdateVersion;
import com.mymanu.companionapp.core.gaia.qtil.data.basic.SystemInformation;
import com.mymanu.companionapp.core.gaia.qtil.data.basic.userfeatures.UserFeatures;

import java.util.List;

public enum DeviceInfo {

    /**
     * Information of type <code>int</code>, expected to be one of {@link GaiaVersion}.
     */
    GAIA_VERSION,
    /**
     * Information of type {@link String}.
     */
    APPLICATION_VERSION,
    /**
     * Information of type {@link String}.
     */
    VARIANT_NAME,
    /**
     * Information of type {@link String}.
     */
    SERIAL_NUMBER,
    /**
     * Information of type {@link UserFeatures}.
     */
    USER_FEATURES,
    /**
     * Information of type {@link ChargerStatus}.
     */
    CHARGER_STATUS,
    /**
     * Information of type {@link List}<{@link SystemInformation}>.
     */
    SYSTEM_INFORMATION,
    /**
     * Information of type {@link CrossUpdateVersion}>.
     */
    CROSS_UPDATE_REQUIRED,
}
