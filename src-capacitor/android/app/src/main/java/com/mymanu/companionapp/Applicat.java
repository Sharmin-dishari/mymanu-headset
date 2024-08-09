package com.mymanu.companionapp;

import android.app.Application;

import com.kfree.cmd.DeviceBleEx;
import com.mymanu.companionapp.repository.anclegacy.ANCRepositoryLegacy;
import com.mymanu.companionapp.repository.audiocuration.AudioCurationRepository;
import com.mymanu.companionapp.repository.battery.BatteryRepository;
import com.mymanu.companionapp.repository.connection.ConnectionRepository;
import com.mymanu.companionapp.repository.discovery.DiscoveryRepository;
import com.mymanu.companionapp.repository.earbudfit.EarbudFitRepository;
import com.mymanu.companionapp.repository.features.FeaturesRepository;
import com.mymanu.companionapp.repository.gestureconfiguration.GestureConfigurationRepository;
import com.mymanu.companionapp.repository.handsetservice.HandsetServiceRepository;
import com.mymanu.companionapp.repository.logs.DeviceLogsRepository;
import com.mymanu.companionapp.repository.musicprocessing.MusicProcessingRepository;
import com.mymanu.companionapp.repository.system.SystemRepository;
import com.mymanu.companionapp.repository.upgrade.UpgradeRepository;
import com.mymanu.companionapp.repository.voiceprocessing.VoiceProcessingRepository;
import com.mymanu.companionapp.core.GaiaClientService;
import com.mymanu.companionapp.core.bluetooth.data.BluetoothStatus;
import com.mymanu.companionapp.core.bluetooth.data.ConnectionState;
import com.mymanu.companionapp.core.bluetooth.data.Device;
import com.mymanu.companionapp.core.publications.qtil.subscribers.ConnectionSubscriber;
import com.mymanu.companionapp.repository.deviceinfo.DeviceInformationRepository;
import com.mymanu.companionapp.repository.mediaplayback.MediaPlaybackRepository;
import com.mymanu.companionapp.repository.service.ServiceRepository;
import com.mymanu.companionapp.repository.statistics.StatisticsRepository;
import com.mymanu.companionapp.repository.voiceui.VoiceUIRepository;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class Applicat extends Application {
    private final static String TAG = "Applicat";

    public static DeviceBleEx gDevBle = null;@Inject

    public DiscoveryRepository discoveryRepository;

    @Inject
    public FeaturesRepository featuresRepository;

    @Inject
    public ConnectionRepository connectionRepository;

    @Inject
    public DeviceInformationRepository deviceInfoRepository;

    @Inject
    public UpgradeRepository upgradeRepository;

    @Inject
    public VoiceUIRepository voiceUIRepository;

    @Inject
    public ANCRepositoryLegacy ancRepositoryLegacy;

    @Inject
    public AudioCurationRepository audioCurationRepository;

    @Inject
    public SystemRepository systemRepository;

    @Inject
    public DeviceLogsRepository deviceLogsRepository;

    @Inject
    public MusicProcessingRepository musicProcessingRepository;

    @Inject
    public EarbudFitRepository earbudFitRepository;

    @Inject
    public HandsetServiceRepository handsetServiceRepository;

    @Inject
    public VoiceProcessingRepository voiceProcessingRepository;

    @Inject
    public MediaPlaybackRepository mediaPlaybackRepository;

    @Inject
    public GestureConfigurationRepository gestureConfigurationRepository;

    @Inject
    public BatteryRepository batteryRepository;

    @Inject
    public StatisticsRepository statisticsRepository;

    @Inject
    public ServiceRepository serviceRepository;

    private final ConnectionSubscriber connectionSubscriber = new ConnectionSubscriber() {
      @Override
      public void onConnectionStateChanged(Device device, ConnectionState state) {
        if (state == ConnectionState.DISCONNECTED) {
          resetRepositories(false);
        }
      }

      @Override
      public void onConnectionError(Device device, BluetoothStatus reason) {
      }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        com.kfree.Log.Config(this,"---bluetoothheadsetdemo---");

        // initialising core
        GaiaClientService.prepare(this);

        // initialising repositories
        featuresRepository.init();
        deviceInfoRepository.init();
        upgradeRepository.init();
        voiceUIRepository.init();
        ancRepositoryLegacy.init();
        systemRepository.init(this);
        deviceLogsRepository.init();
        musicProcessingRepository.init();
        audioCurationRepository.init();
        earbudFitRepository.init();
        handsetServiceRepository.init();
        voiceProcessingRepository.init();
        gestureConfigurationRepository.init();
        batteryRepository.init();
        statisticsRepository.init(featuresRepository);
        serviceRepository.init();

        // subscribe the connection subscriber to reset the repositories on disconnection
        GaiaClientService.getPublicationManager().subscribe(connectionSubscriber);
    }

    @Override
    public void onTerminate() {
      super.onTerminate();
      // releasing core
      GaiaClientService.getPublicationManager().unsubscribe(connectionSubscriber);
      GaiaClientService.release(this);
    }

    public void resetRepositories(boolean hardReset) {
      ancRepositoryLegacy.reset();
      audioCurationRepository.reset();
      earbudFitRepository.reset();
      featuresRepository.reset();
      handsetServiceRepository.reset();
      deviceLogsRepository.reset(this);
      musicProcessingRepository.reset();
      voiceUIRepository.reset();
      voiceProcessingRepository.reset();
      gestureConfigurationRepository.reset();
      batteryRepository.reset();
      statisticsRepository.reset(hardReset);
      serviceRepository.reset();

      if (hardReset) {
        deviceInfoRepository.reset();
        upgradeRepository.reset();
      }

      // not resetting:
      //  1. ConnectionRepository: data needs to be kept through the lifecycle of the app
      //  2. SystemRepository or MediaPlaybackRepository: data not related to a device
    }

}

