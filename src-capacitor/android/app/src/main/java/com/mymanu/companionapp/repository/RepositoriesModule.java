/*
 * ************************************************************************************************
 * * © 2021-2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.repository;

import com.mymanu.companionapp.repository.anclegacy.ANCRepositoryLegacy;
import com.mymanu.companionapp.repository.anclegacy.ANCRepositoryLegacyImpl;
import com.mymanu.companionapp.repository.audiocuration.AudioCurationRepository;
import com.mymanu.companionapp.repository.audiocuration.AudioCurationRepositoryImpl;
import com.mymanu.companionapp.repository.battery.BatteryRepository;
import com.mymanu.companionapp.repository.battery.BatteryRepositoryImpl;
import com.mymanu.companionapp.repository.connection.ConnectionRepository;
import com.mymanu.companionapp.repository.connection.ConnectionRepositoryImpl;
import com.mymanu.companionapp.repository.deviceinfo.DeviceInformationRepository;
import com.mymanu.companionapp.repository.deviceinfo.DeviceInformationRepositoryImpl;
import com.mymanu.companionapp.repository.discovery.DiscoveryRepository;
import com.mymanu.companionapp.repository.discovery.DiscoveryRepositoryImpl;
import com.mymanu.companionapp.repository.earbudfit.EarbudFitRepository;
import com.mymanu.companionapp.repository.earbudfit.EarbudFitRepositoryImpl;
import com.mymanu.companionapp.repository.features.FeaturesRepository;
import com.mymanu.companionapp.repository.features.FeaturesRepositoryImpl;
import com.mymanu.companionapp.repository.gestureconfiguration.GestureConfigurationRepository;
import com.mymanu.companionapp.repository.gestureconfiguration.GestureConfigurationRepositoryImpl;
import com.mymanu.companionapp.repository.handsetservice.HandsetServiceRepository;
import com.mymanu.companionapp.repository.handsetservice.HandsetServiceRepositoryImpl;
import com.mymanu.companionapp.repository.logs.DeviceLogsRepository;
import com.mymanu.companionapp.repository.logs.DeviceLogsRepositoryImpl;
import com.mymanu.companionapp.repository.mediaplayback.MediaPlaybackRepository;
import com.mymanu.companionapp.repository.mediaplayback.MediaPlaybackRepositoryImpl;
import com.mymanu.companionapp.repository.musicprocessing.MusicProcessingRepository;
import com.mymanu.companionapp.repository.musicprocessing.MusicProcessingRepositoryImpl;
import com.mymanu.companionapp.repository.service.ServiceRepository;
import com.mymanu.companionapp.repository.service.ServiceRepositoryImpl;
import com.mymanu.companionapp.repository.statistics.StatisticsRepository;
import com.mymanu.companionapp.repository.statistics.StatisticsRepositoryImpl;
import com.mymanu.companionapp.repository.system.SystemRepository;
import com.mymanu.companionapp.repository.system.SystemRepositoryImpl;
import com.mymanu.companionapp.repository.upgrade.UpgradeRepository;
import com.mymanu.companionapp.repository.upgrade.UpgradeRepositoryImpl;
import com.mymanu.companionapp.repository.voiceprocessing.VoiceProcessingRepository;
import com.mymanu.companionapp.repository.voiceprocessing.VoiceProcessingRepositoryImpl;
import com.mymanu.companionapp.repository.voiceui.VoiceUIRepository;
import com.mymanu.companionapp.repository.voiceui.VoiceUIRepositoryImpl;
import com.mymanu.companionapp.repository.xpan.XpanRepository;
import com.mymanu.companionapp.repository.xpan.XpanRepositoryImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class RepositoriesModule {

    @Binds
    @Singleton
    public abstract ANCRepositoryLegacy bindANCRepository(ANCRepositoryLegacyImpl impl);

    @Binds
    @Singleton
    public abstract AudioCurationRepository bindAudioCurationRepository(AudioCurationRepositoryImpl impl);

    @Binds
    @Singleton
    public abstract ConnectionRepository bindConnectionRepository(ConnectionRepositoryImpl impl);

    @Binds
    @Singleton
    public abstract DeviceInformationRepository bindDeviceInformationRepository(DeviceInformationRepositoryImpl impl);

    @Binds
    @Singleton
    public abstract DeviceLogsRepository bindDeviceLogsRepository(DeviceLogsRepositoryImpl impl);

    @Binds
    @Singleton
    public abstract DiscoveryRepository bindDiscoveryRepository(DiscoveryRepositoryImpl impl);

    @Binds
    @Singleton
    public abstract FeaturesRepository bindFeaturesRepository(FeaturesRepositoryImpl impl);

    @Binds
    @Singleton
    public abstract HandsetServiceRepository bindHandsetServiceRepository(HandsetServiceRepositoryImpl impl);

    @Binds
    @Singleton
    public abstract MediaPlaybackRepository bindMediaPlaybackRepository(MediaPlaybackRepositoryImpl impl);

    @Binds
    @Singleton
    public abstract MusicProcessingRepository bindMusicProcessingRepository(MusicProcessingRepositoryImpl impl);

    @Binds
    @Singleton
    public abstract SystemRepository bindSystemRepository(SystemRepositoryImpl impl);

    @Binds
    @Singleton
    public abstract UpgradeRepository bindUpgradeRepository(UpgradeRepositoryImpl impl);

    @Binds
    @Singleton
    public abstract VoiceUIRepository bindVoiceUIRepository(VoiceUIRepositoryImpl impl);

    @Binds
    @Singleton
    public abstract VoiceProcessingRepository bindVoiceProcessingRepository(VoiceProcessingRepositoryImpl impl);

    @Binds
    @Singleton
    public abstract EarbudFitRepository bindEarbudFitRepository(EarbudFitRepositoryImpl impl);

    @Binds
    @Singleton
    public abstract GestureConfigurationRepository bindGestureConfigurationRepository(
            GestureConfigurationRepositoryImpl impl);

    @Binds
    @Singleton
    public abstract BatteryRepository bindBatteryRepository(BatteryRepositoryImpl impl);

    @Binds
    @Singleton
    public abstract StatisticsRepository bindStatisticsRepository(StatisticsRepositoryImpl impl);

    @Binds
    @Singleton
    public abstract ServiceRepository bindServiceRepository(ServiceRepositoryImpl impl);

    @Binds
    @Singleton
    public abstract XpanRepository bindXpanRepository(XpanRepositoryImpl impl);

}
