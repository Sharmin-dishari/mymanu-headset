/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.subscribers;

import androidx.annotation.NonNull;

import com.mymanu.companionapp.core.data.GestureConfigurationInfo;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.data.gestures.Action;
import com.mymanu.companionapp.core.gaia.qtil.data.gestures.Configuration;
import com.mymanu.companionapp.core.gaia.qtil.data.gestures.Gesture;
import com.mymanu.companionapp.core.gaia.qtil.data.gestures.GestureContext;
import com.mymanu.companionapp.core.gaia.qtil.data.gestures.TouchpadConfiguration;
import com.mymanu.companionapp.core.publications.core.Subscriber;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;

import java.util.Set;

public interface GestureConfigurationSubscriber extends Subscriber {

    @NonNull
    default Subscription getSubscription() {
        return CoreSubscription.GESTURE_CONFIGURATION;
    }

    void onNumberOfTouchpads(TouchpadConfiguration configuration);

    void onGestures(Set<Gesture> gestures);

    void onGestureContexts(Set<GestureContext> contexts);

    void onActions(Set<Action> actions);

    void onConfiguration(Gesture gesture, Set<Configuration> configurations);

    void onConfigurationChanged(Gesture gesture);

    void onConfigurationsReset();

    void onError(GestureConfigurationInfo info, Reason reason);
}
