/*
 * ************************************************************************************************
 * * © 2020-2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.publishers;

import com.mymanu.companionapp.core.gaia.qtil.data.VoiceAssistant;
import com.mymanu.companionapp.core.publications.core.Publisher;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;
import com.mymanu.companionapp.core.publications.qtil.subscribers.VoiceUISubscriber;

import java.util.List;

public class VoiceUIPublisher extends Publisher<VoiceUISubscriber> {

    @Override
    public Subscription getSubscription() {
        return CoreSubscription.VOICE_UI;
    }

    public void publishSelectedAssistant(VoiceAssistant assistant) {
        forEachSubscriber(subscriber -> subscriber.onSelectedAssistant(assistant));
    }

    public void publishAssistants(List<VoiceAssistant> assistants) {
        forEachSubscriber(subscriber -> subscriber.onSupportedAssistants(assistants));
    }

}
