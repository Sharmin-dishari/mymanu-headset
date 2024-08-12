/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.repository.voiceprocessing;

import android.content.Context;

import androidx.annotation.NonNull;

import com.mymanu.companionapp.core.GaiaClientService;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.data.VoiceProcessingInfo;
import com.mymanu.companionapp.core.gaia.qtil.data.CVC3MicConfiguration;
import com.mymanu.companionapp.core.gaia.qtil.data.CVCBypassMode;
import com.mymanu.companionapp.core.gaia.qtil.data.Capability;
import com.mymanu.companionapp.core.gaia.qtil.data.VoiceEnhancement;
import com.mymanu.companionapp.core.gaia.qtil.data.VoiceEnhancementConfiguration;
import com.mymanu.companionapp.core.publications.PublicationManager;
import com.mymanu.companionapp.core.publications.qtil.subscribers.VoiceProcessingSubscriber;
import com.mymanu.companionapp.core.requests.qtil.GetSupportedVoiceEnhancementsRequest;
import com.mymanu.companionapp.core.requests.qtil.GetVoiceEnhancementConfigurationRequest;
import com.mymanu.companionapp.core.requests.qtil.SetVoiceEnhancementConfigurationRequest;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class VoiceProcessingRepositoryImpl extends VoiceProcessingRepositoryData {

    private final VoiceProcessingSubscriber voiceProcessingSubscriber = new VoiceProcessingSubscriber() {
        @Override
        public void onEnhancements(@NonNull ArrayList<VoiceEnhancement> enhancements) {
            updateEnhancements(enhancements);
        }

        @Override
        public void onConfiguration(@NonNull VoiceEnhancementConfiguration configuration) {
            if (configuration.getCapability() == Capability.CVC_3MIC && configuration instanceof CVC3MicConfiguration) {
                CVC3MicConfiguration cvcConfig = (CVC3MicConfiguration) configuration;
                updateMicrophoneMode(cvcConfig.getMicrophoneMode());
                updateBypassMode(cvcConfig.getBypassMode());
                updateOperationMode(cvcConfig.getOperationMode());
            }
        }

        @Override
        public void onError(VoiceProcessingInfo info, Reason reason) {
            updateAndClearError(info, reason);
        }
    };

    @Inject
    public VoiceProcessingRepositoryImpl() {

    }

    @Override
    public void init() {
        PublicationManager publicationManager = GaiaClientService.getPublicationManager();
        publicationManager.subscribe(voiceProcessingSubscriber);
    }

    @Override
    public void fetchEnhancements(Context context) {
        GetSupportedVoiceEnhancementsRequest request = new GetSupportedVoiceEnhancementsRequest();
        GaiaClientService.getRequestManager().execute(context, request);

    }

    @Override
    public void fetchConfiguration(Context context, Capability capability) {
        GetVoiceEnhancementConfigurationRequest request = new GetVoiceEnhancementConfigurationRequest(capability);
        GaiaClientService.getRequestManager().execute(context, request);
    }

    @Override
    public void setBypassMode(Context context, @NonNull CVCBypassMode mode) {
        setCVC3MicConfiguration(context, getMicrophoneMode(), mode);
    }

    @Override
    public void setMicrophoneMode(Context context, int mode) {
        setCVC3MicConfiguration(context, mode, getBypassMode());
    }

    private void setCVC3MicConfiguration(Context context, int microphoneMode, CVCBypassMode mode) {
        VoiceEnhancementConfiguration configuration = new CVC3MicConfiguration(microphoneMode, mode);
        SetVoiceEnhancementConfigurationRequest request = new SetVoiceEnhancementConfigurationRequest(configuration);
        GaiaClientService.getRequestManager().execute(context, request);
    }
}
