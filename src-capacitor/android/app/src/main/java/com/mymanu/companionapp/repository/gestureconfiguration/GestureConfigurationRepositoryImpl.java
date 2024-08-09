/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.repository.gestureconfiguration;

import android.content.Context;

import com.mymanu.companionapp.core.GaiaClientService;
import com.mymanu.companionapp.core.data.GestureConfigurationInfo;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.data.gestures.Action;
import com.mymanu.companionapp.core.gaia.qtil.data.gestures.Configuration;
import com.mymanu.companionapp.core.gaia.qtil.data.gestures.Gesture;
import com.mymanu.companionapp.core.gaia.qtil.data.gestures.GestureContext;
import com.mymanu.companionapp.core.gaia.qtil.data.gestures.SetGestureConfiguration;
import com.mymanu.companionapp.core.gaia.qtil.data.gestures.TouchpadConfiguration;
import com.mymanu.companionapp.core.publications.PublicationManager;
import com.mymanu.companionapp.core.publications.qtil.subscribers.GestureConfigurationSubscriber;
import com.mymanu.companionapp.core.requests.qtil.GetGestureInfoRequest;
import com.mymanu.companionapp.core.requests.qtil.ResetGestureConfigurationsToDefaultsRequest;
import com.mymanu.companionapp.core.requests.qtil.SetGestureConfigurationRequest;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GestureConfigurationRepositoryImpl extends GestureConfigurationRepositoryData {

    private final GestureConfigurationSubscriber gestureConfigurationSubscriber = new GestureConfigurationSubscriber() {
        @Override
        public void onNumberOfTouchpads(TouchpadConfiguration configuration) {
            updateTouchpadConfiguration(configuration);
        }

        @Override
        public void onGestures(Set<Gesture> gestures) {
            updateGestures(gestures);
        }

        @Override
        public void onGestureContexts(Set<GestureContext> contexts) {
            updateContexts(contexts);
        }

        @Override
        public void onActions(Set<Action> actions) {
            updateActions(actions);
        }

        @Override
        public void onConfiguration(Gesture gesture, Set<Configuration> configurations) {
            updateConfigurations(gesture, configurations);
        }

        @Override
        public void onConfigurationChanged(Gesture gesture) {
            fetchConfiguration(null, gesture);
        }

        @Override
        public void onConfigurationsReset() {
            resetConfigurations();
        }

        @Override
        public void onError(GestureConfigurationInfo info, Reason reason) {
        }
    };

    @Inject
    public GestureConfigurationRepositoryImpl() {

    }

    @Override
    public void init() {
        PublicationManager publicationManager = GaiaClientService.getPublicationManager();
        publicationManager.subscribe(gestureConfigurationSubscriber);
    }

    @Override
    public void fetchData(Context context, GestureConfigurationInfo info) {
        GetGestureInfoRequest request = new GetGestureInfoRequest(info);
        GaiaClientService.getRequestManager().execute(context, request);
    }

    @Override
    public void fetchConfiguration(Context context, Gesture gesture) {
        GetGestureInfoRequest request =
                new GetGestureInfoRequest(GestureConfigurationInfo.GET_GESTURE_CONFIGURATION, gesture.getId());
        GaiaClientService.getRequestManager().execute(context, request);
    }

    @Override
    public void resetToDefaults(Context context) {
        ResetGestureConfigurationsToDefaultsRequest request = new ResetGestureConfigurationsToDefaultsRequest();
        GaiaClientService.getRequestManager().execute(context, request);
    }

    @Override
    public void setGestureConfigurations(Context context, Gesture gesture, Set<Configuration> configurations) {
        SetGestureConfiguration setter = new SetGestureConfiguration(gesture.getId(), configurations);
        SetGestureConfigurationRequest request = new SetGestureConfigurationRequest(setter);
        GaiaClientService.getRequestManager().execute(context, request);
    }
}
