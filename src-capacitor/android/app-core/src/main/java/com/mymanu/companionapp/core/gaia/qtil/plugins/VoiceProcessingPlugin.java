/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.gaia.qtil.plugins;

import com.mymanu.companionapp.core.gaia.qtil.data.Capability;
import com.mymanu.companionapp.core.gaia.qtil.data.VoiceEnhancementConfiguration;
import com.mymanu.companionapp.core.publications.qtil.publishers.VoiceProcessingPublisher;

public interface VoiceProcessingPlugin {

    // to force the VoiceProcessingPlugin to implement VoiceProcessingPublisher
    // this is unused
    VoiceProcessingPublisher getVoiceProcessingPublisher();

    void getSupportedEnhancements();

    void getConfiguration(Capability capability);

    void setConfiguration(VoiceEnhancementConfiguration configuration);
}
