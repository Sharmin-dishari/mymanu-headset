/*
 * ************************************************************************************************
 * * © 2021, 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.       *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.repository.upgrade;

import android.content.Context;

import androidx.annotation.NonNull;

import com.mymanu.companionapp.core.GaiaClientService;
import com.mymanu.companionapp.core.publications.PublicationManager;
import com.mymanu.companionapp.core.publications.qtil.subscribers.UpgradeSubscriber;
import com.mymanu.companionapp.core.requests.RequestManager;
import com.mymanu.companionapp.core.requests.core.RequestListener;
import com.mymanu.companionapp.core.requests.qtil.AbortUpgradeRequest;
import com.mymanu.companionapp.core.requests.qtil.ConfirmUpgradeRequest;
import com.mymanu.companionapp.core.requests.qtil.StartUpgradeRequest;
import com.mymanu.companionapp.core.upgrade.data.ChunkSizeType;
import com.mymanu.companionapp.core.upgrade.data.UpdateOptions;
import com.mymanu.companionapp.core.upgrade.data.UpgradeConfirmation;
import com.mymanu.companionapp.core.upgrade.data.UpgradeErrorStatus;
import com.mymanu.companionapp.core.upgrade.data.UpgradeFail;
import com.mymanu.companionapp.core.upgrade.data.UpgradeInfoType;
import com.mymanu.companionapp.core.upgrade.data.UpgradeProgress;
import com.mymanu.companionapp.repository.deviceinfo.DeviceInformationRepository;
import com.mymanu.companionapp.libraries.upgrade.data.ConfirmationOptions;
import com.mymanu.companionapp.libraries.upgrade.messages.UpgradeAlert;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class UpgradeRepositoryImpl extends UpgradeRepositoryData {

    private final UpgradeSubscriber mUpgradeSubscriber = new UpgradeSubscriber() {
        @Override
        public void onProgress(UpgradeProgress progress) {
            if (progress.getType() == UpgradeInfoType.END) {
                onUpgradeEnd(progress);
            }
            else {
                onUpgradeProgress(progress);
            }
        }

        @Override
        public void onError(UpgradeFail error) {
            onUpgradeError(error);
        }

        @Override
        public void onChunkSize(ChunkSizeType type, int size) {
            switch (type) {
                case SET:
                    setSize(SizeType.SET, size);
                    break;
                case AVAILABLE:
                    setSize(SizeType.MAX, size);
                    break;
                case DEFAULT:
                    setSize(SizeType.DEFAULT, size);
                    break;
            }
        }

        @Override
        public void onAlert(UpgradeAlert alert, boolean raised) {
            updateAlert(alert, raised);
        }
    };

    @Inject
    public UpgradeRepositoryImpl(DeviceInformationRepository deviceInformationRepository) {
        super(deviceInformationRepository);
    }

    @Override
    public void init() {
        PublicationManager publicationManager = GaiaClientService.getPublicationManager();
        publicationManager.subscribe(mUpgradeSubscriber);
    }

    @Override
    protected void executeUpgrade(Context context, UpdateOptions options) {
        // starting the upgrade
        StartUpgradeRequest request = new StartUpgradeRequest(options,
                                                              buildUpgradeProgressListener(
                                                                      UpgradeErrorStatus.GAIA_INITIALISATION_ERROR));
        RequestManager requestManager = GaiaClientService.getRequestManager();
        requestManager.execute(context.getApplicationContext(), request);
    }

    @Override
    protected void executeAbort(Context context) {
        AbortUpgradeRequest request = new AbortUpgradeRequest();

        RequestManager requestManager = GaiaClientService.getRequestManager();
        requestManager.execute(context.getApplicationContext(), request);
    }

    @Override
    protected void executeConfirmation(Context context, UpgradeConfirmation confirmation,
                                       @NonNull ConfirmationOptions option) {
        ConfirmUpgradeRequest request = new ConfirmUpgradeRequest(confirmation, option,
                                                                  buildUpgradeProgressListener(
                                                                          UpgradeErrorStatus.UPGRADE_PROCESS_ERROR));

        RequestManager requestManager = GaiaClientService.getRequestManager();
        requestManager.execute(context.getApplicationContext(), request);
    }

    private RequestListener<Void, Void, Void> buildUpgradeProgressListener(UpgradeErrorStatus status) {
        return new RequestListener<Void, Void, Void>() {
            @Override
            public void onProgress(Void aVoid) {
                // nothing to do
            }

            @Override
            public void onComplete(Void aVoid) {
                // nothing to do
            }

            @Override
            public void onError(Void aVoid) {
                onUpgradeError(new UpgradeFail(status));
            }
        };
    }
}
