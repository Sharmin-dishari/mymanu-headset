/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.publishers;

import com.mymanu.companionapp.core.data.MusicProcessingInfo;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.data.BandInfo;
import com.mymanu.companionapp.core.gaia.qtil.data.EQState;
import com.mymanu.companionapp.core.gaia.qtil.data.PreSet;
import com.mymanu.companionapp.core.publications.core.Publisher;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;
import com.mymanu.companionapp.core.publications.qtil.subscribers.MusicProcessingSubscriber;

import java.util.List;

public class MusicProcessingPublisher extends Publisher<MusicProcessingSubscriber> {

    @Override
    public Subscription getSubscription() {
        return CoreSubscription.MUSIC_PROCESSING;
    }

    public void publishEqState(EQState state) {
        forEachSubscriber(subscriber -> subscriber.onEQInfo(MusicProcessingInfo.EQ_STATE, state));
    }

    public void publishAvailablePreSets(List<PreSet> presets) {
        forEachSubscriber(subscriber -> subscriber.onEQInfo(MusicProcessingInfo.AVAILABLE_PRE_SETS, presets));
    }

    public void publishSelectedSet(PreSet set) {
        forEachSubscriber(subscriber -> subscriber.onEQInfo(MusicProcessingInfo.SELECTED_SET, set));
    }

    public void publishBandsNumber(int count) {
        forEachSubscriber(subscriber -> subscriber.onEQInfo(MusicProcessingInfo.USER_SET_BANDS_NUMBER, count));
    }

    public void publishUserSetConfiguration(List<BandInfo> bands) {
        forEachSubscriber(subscriber -> subscriber.onEQInfo(MusicProcessingInfo.USER_SET_CONFIGURATION, bands));
    }

    public void publishBandChanged(int[] bands) {
        forEachSubscriber(subscriber -> subscriber.onEQInfo(MusicProcessingInfo.BAND_CHANGE, bands));
    }

    public void publishError(MusicProcessingInfo info, Reason reason) {
        forEachSubscriber(subscriber -> subscriber.onEQError(info, reason));
    }
}
