/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.subscribers;

import androidx.annotation.NonNull;

import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.data.VoiceProcessingInfo;
import com.mymanu.companionapp.core.gaia.qtil.data.VoiceEnhancement;
import com.mymanu.companionapp.core.gaia.qtil.data.VoiceEnhancementConfiguration;
import com.mymanu.companionapp.core.publications.core.Subscriber;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;

import java.util.ArrayList;

public interface VoiceProcessingSubscriber extends Subscriber {

    @NonNull
    default Subscription getSubscription() {
        return CoreSubscription.VOICE_PROCESSING;
    }

    void onEnhancements(@NonNull ArrayList<VoiceEnhancement> enhancements);

    void onConfiguration(@NonNull VoiceEnhancementConfiguration configuration);

    void onError(VoiceProcessingInfo info, Reason reason);
}
