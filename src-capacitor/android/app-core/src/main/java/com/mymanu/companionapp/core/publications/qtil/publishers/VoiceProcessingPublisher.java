/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.publishers;

import androidx.annotation.NonNull;

import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.data.VoiceProcessingInfo;
import com.mymanu.companionapp.core.gaia.qtil.data.VoiceEnhancement;
import com.mymanu.companionapp.core.gaia.qtil.data.VoiceEnhancementConfiguration;
import com.mymanu.companionapp.core.publications.core.Publisher;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;
import com.mymanu.companionapp.core.publications.qtil.subscribers.VoiceProcessingSubscriber;

import java.util.ArrayList;

public class VoiceProcessingPublisher extends Publisher<VoiceProcessingSubscriber> {

    @Override
    public Subscription getSubscription() {
        return CoreSubscription.VOICE_PROCESSING;
    }

    public void publishEnhancements(@NonNull ArrayList<VoiceEnhancement> enhancements) {
        forEachSubscriber(subscriber -> subscriber.onEnhancements(enhancements));
    }

    public void publishConfiguration(@NonNull VoiceEnhancementConfiguration configuration) {
        forEachSubscriber(subscriber -> subscriber.onConfiguration(configuration));
    }

    public void publishError(VoiceProcessingInfo info, Reason reason) {
        forEachSubscriber(subscriber -> subscriber.onError(info, reason));
    }

}
