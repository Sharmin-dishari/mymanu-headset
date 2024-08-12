/*
 * ************************************************************************************************
 * * © 2020-2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil;

import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.publishers.DeviceInformationPublisher;
import com.mymanu.companionapp.core.publications.qtil.publishers.GestureConfigurationPublisher;
import com.mymanu.companionapp.core.publications.qtil.publishers.HandsetServicePublisher;
import com.mymanu.companionapp.core.publications.qtil.publishers.MusicProcessingPublisher;
import com.mymanu.companionapp.core.publications.qtil.publishers.VoiceProcessingPublisher;
import com.mymanu.companionapp.core.publications.qtil.publishers.ANCPublisher;
import com.mymanu.companionapp.core.publications.qtil.publishers.AudioCurationPublisher;
import com.mymanu.companionapp.core.publications.qtil.publishers.BatteryPublisher;
import com.mymanu.companionapp.core.publications.qtil.publishers.BluetoothPublisher;
import com.mymanu.companionapp.core.publications.qtil.publishers.ConnectionPublisher;
import com.mymanu.companionapp.core.publications.qtil.publishers.DebugPublisher;
import com.mymanu.companionapp.core.publications.qtil.publishers.EarbudFitPublisher;
import com.mymanu.companionapp.core.publications.qtil.publishers.EarbudPublisher;
import com.mymanu.companionapp.core.publications.qtil.publishers.HandoverPublisher;
import com.mymanu.companionapp.core.publications.qtil.publishers.ProtocolPublisher;
import com.mymanu.companionapp.core.publications.qtil.publishers.QTILFeaturesPublisher;
import com.mymanu.companionapp.core.publications.qtil.publishers.UpgradePublisher;
import com.mymanu.companionapp.core.publications.qtil.publishers.VoiceUIPublisher;
import com.mymanu.companionapp.core.publications.qtil.subscribers.DeviceInformationSubscriber;
import com.mymanu.companionapp.core.publications.qtil.subscribers.GestureConfigurationSubscriber;
import com.mymanu.companionapp.core.publications.qtil.subscribers.HandsetServiceSubscriber;
import com.mymanu.companionapp.core.publications.qtil.subscribers.MusicProcessingSubscriber;
import com.mymanu.companionapp.core.publications.qtil.subscribers.VoiceProcessingSubscriber;
import com.mymanu.companionapp.core.publications.qtil.subscribers.ANCSubscriber;
import com.mymanu.companionapp.core.publications.qtil.subscribers.AudioCurationSubscriber;
import com.mymanu.companionapp.core.publications.qtil.subscribers.BatterySubscriber;
import com.mymanu.companionapp.core.publications.qtil.subscribers.BluetoothSubscriber;
import com.mymanu.companionapp.core.publications.qtil.subscribers.ConnectionSubscriber;
import com.mymanu.companionapp.core.publications.qtil.subscribers.DebugSubscriber;
import com.mymanu.companionapp.core.publications.qtil.subscribers.EarbudFitSubscriber;
import com.mymanu.companionapp.core.publications.qtil.subscribers.EarbudSubscriber;
import com.mymanu.companionapp.core.publications.qtil.subscribers.HandoverSubscriber;
import com.mymanu.companionapp.core.publications.qtil.subscribers.ProtocolSubscriber;
import com.mymanu.companionapp.core.publications.qtil.subscribers.QTILFeaturesSubscriber;
import com.mymanu.companionapp.core.publications.qtil.subscribers.UpgradeSubscriber;
import com.mymanu.companionapp.core.publications.qtil.subscribers.VoiceUISubscriber;

/**
 * All the subscriptions defined in the core module.
 */
public enum CoreSubscription implements Subscription {

    /**
     * This subscription is mapped with:
     * <ul>
     * <li>Publisher:
     * {@link ConnectionPublisher}
     * </li>
     * <li>Subscriber:
     * {@link ConnectionSubscriber}</li>
     * </ul>
     */
    CONNECTION,
    /**
     * This subscription is mapped with:
     * <ul>
     * <li>Publisher:
     * {@link QTILFeaturesPublisher}
     * </li>
     * <li>Subscriber:
     * {@link QTILFeaturesSubscriber}
     * </li>
     * </ul>
     */
    FEATURES,
    /**
     * This subscription is mapped with:
     * <ul>
     * <li>Publisher:
     * {@link DeviceInformationPublisher}
     * </li>
     * <li>Subscriber:
     * {@link DeviceInformationSubscriber}</li>
     * </ul>
     */
    DEVICE_INFORMATION,
    /**
     * This subscription is mapped with:
     * <ul>
     * <li>Publisher:
     * {@link UpgradePublisher}
     * </li>
     * <li>Subscriber:
     * {@link UpgradeSubscriber}</li>
     * </ul>
     */
    UPGRADE,
    /**
     * This subscription is mapped with:
     * <ul>
     * <li>Publisher:
     * {@link VoiceUIPublisher}
     * </li>
     * <li>Subscriber:
     * {@link VoiceUISubscriber}</li>
     * </ul>
     */
    VOICE_UI,
    /**
     * This subscription is mapped with:
     * <ul>
     * <li>Publisher:
     * {@link ANCPublisher}
     * </li>
     * <li>Subscriber:
     * {@link ANCSubscriber}</li>
     * </ul>
     */
    ANC,
    /**
     * This subscription is mapped with:
     * <ul>
     * <li>Publisher:
     * {@link AudioCurationPublisher}
     * </li>
     * <li>Subscriber:
     * {@link AudioCurationSubscriber}</li>
     * </ul>
     */
    AUDIO_CURATION,
    /**
     * This subscription is mapped with:
     * <ul>
     * <li>Publisher:
     * {@link EarbudPublisher}
     * </li>
     * <li>Subscriber:
     * <p>
     * {@link EarbudSubscriber}
     * </li>
     * </ul>
     */
    EARBUD,
    /**
     * This subscription is mapped with:
     * <ul>
     * <li>Publisher:
     * {@link HandoverPublisher}
     * </li>
     * <li>Subscriber:
     * {@link HandoverSubscriber}
     * </li>
     * </ul>
     */
    HANDOVER,
    /**
     * This subscription is mapped with:
     * <ul>
     * <li>Publisher:
     * {@link ProtocolPublisher}
     * </li>
     * <li>Subscriber:
     * {@link ProtocolSubscriber}
     * </li>
     * </ul>
     */
    TRANSPORT_INFORMATION,
    /**
     * This subscription is mapped with:
     * <ul>
     * <li>Publisher:
     * {@link BluetoothPublisher}
     * </li>
     * <li>Subscriber:
     * {@link BluetoothSubscriber}
     * </li>
     * </ul>
     */
    BLUETOOTH,
    /**
     * This subscription is mapped with:
     * <ul>
     * <li>Publisher:
     * {@link DebugPublisher}
     * </li>
     * <li>Subscriber:
     * {@link DebugSubscriber}
     * </li>
     * </ul>
     */
    DEBUG,
    /**
     * This subscription is mapped with:
     * <ul>
     * <li>Publisher:
     * {@link MusicProcessingPublisher}
     * </li>
     * <li>Subscriber:
     * {@link MusicProcessingSubscriber}
     * </li>
     * </ul>
     */
    MUSIC_PROCESSING,
    /**
     * This subscription is mapped with:
     * <ul>
     * <li>Publisher:
     * {@link EarbudFitPublisher}
     * </li>
     * <li>Subscriber:
     * {@link EarbudFitSubscriber}
     * </li>
     * </ul>
     */
    EARBUD_FIT,
    /**
     * This subscription is mapped with:
     * <ul>
     * <li>Publisher:
     * {@link HandsetServicePublisher}
     * </li>
     * <li>Subscriber:
     * {@link HandsetServiceSubscriber}
     * </li>
     * </ul>
     */
    HANDSET_SERVICE,
    /**
     * This subscription is mapped with:
     * <ul>
     * <li>Publisher:
     * {@link VoiceProcessingPublisher}
     * </li>
     * <li>Subscriber:
     * {@link VoiceProcessingSubscriber}
     * </li>
     * </ul>
     */
    VOICE_PROCESSING,
    /**
     * This subscription is mapped with:
     * <ul>
     * <li>Publisher:
     * {@link GestureConfigurationPublisher}
     * </li>
     * <li>Subscriber:
     * {@link GestureConfigurationSubscriber}
     * </li>
     * </ul>
     */
    GESTURE_CONFIGURATION,
    /**
     * This subscription is mapped with:
     * <ul>
     * <li>Publisher:
     * {@link BatteryPublisher}
     * </li>
     * <li>Subscriber:
     * {@link BatterySubscriber}
     * </li>
     * </ul>
     */
    BATTERY,
    /**
     * This subscription is mapped with:
     * <ul>
     * <li>Publisher:
     * {@link com.mymanu.companionapp.core.publications.qtil.publishers.StatisticsPublisher}
     * </li>
     * <li>Subscriber:
     * {@link com.mymanu.companionapp.core.publications.qtil.subscribers.StatisticsSubscriber}
     * </li>
     * </ul>
     */
    STATISTICS

}
