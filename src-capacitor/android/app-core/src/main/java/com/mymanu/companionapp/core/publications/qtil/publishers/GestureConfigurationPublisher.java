/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.publishers;

import com.mymanu.companionapp.core.data.GestureConfigurationInfo;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.data.gestures.Action;
import com.mymanu.companionapp.core.gaia.qtil.data.gestures.Configuration;
import com.mymanu.companionapp.core.gaia.qtil.data.gestures.Gesture;
import com.mymanu.companionapp.core.gaia.qtil.data.gestures.GestureContext;
import com.mymanu.companionapp.core.gaia.qtil.data.gestures.TouchpadConfiguration;
import com.mymanu.companionapp.core.publications.core.Publisher;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;
import com.mymanu.companionapp.core.publications.qtil.subscribers.GestureConfigurationSubscriber;

import java.util.Set;

public class GestureConfigurationPublisher extends Publisher<GestureConfigurationSubscriber> {

    @Override
    public Subscription getSubscription() {
        return CoreSubscription.GESTURE_CONFIGURATION;
    }

    public void publishNumberOfTouchpads(TouchpadConfiguration configuration) {
        forEachSubscriber(subscriber -> subscriber.onNumberOfTouchpads(configuration));
    }

    public void publishGestures(Set<Gesture> gestures) {
        forEachSubscriber(subscriber -> subscriber.onGestures(gestures));
    }

    public void publishGestureContexts(Set<GestureContext> contexts) {
        forEachSubscriber(subscriber -> subscriber.onGestureContexts(contexts));
    }

    public void publishActions(Set<Action> actions) {
        forEachSubscriber(subscriber -> subscriber.onActions(actions));
    }

    public void publishConfiguration(Gesture gesture, Set<Configuration> configurations) {
        forEachSubscriber(subscriber -> subscriber.onConfiguration(gesture, configurations));
    }

    public void publishConfigurationChanged(Gesture gesture) {
        forEachSubscriber(subscriber -> subscriber.onConfigurationChanged(gesture));
    }

    public void publishConfigurationsReset() {
        forEachSubscriber(GestureConfigurationSubscriber::onConfigurationsReset);
    }

    public void publishError(GestureConfigurationInfo info, Reason reason) {
        forEachSubscriber(subscriber -> subscriber.onError(info, reason));
    }
}
