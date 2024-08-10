/*
 * ************************************************************************************************
 * * © 2020-2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.subscribers;

import com.mymanu.companionapp.core.gaia.qtil.data.VoiceAssistant;
import com.mymanu.companionapp.core.publications.core.Subscriber;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;

import java.util.List;

import androidx.annotation.NonNull;

public interface VoiceUISubscriber extends Subscriber {

    @NonNull
    default Subscription getSubscription() {
        return CoreSubscription.VOICE_UI;
    }

    void onSelectedAssistant(VoiceAssistant assistant);

    void onSupportedAssistants(List<VoiceAssistant> assistants);

}
