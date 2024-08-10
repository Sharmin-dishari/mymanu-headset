/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.repository.handsetservice;

import android.content.Context;

import com.mymanu.companionapp.core.GaiaClientService;
import com.mymanu.companionapp.core.data.HandsetServiceInfo;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.data.MultipointType;
import com.mymanu.companionapp.core.publications.PublicationManager;
import com.mymanu.companionapp.core.publications.qtil.subscribers.HandsetServiceSubscriber;
import com.mymanu.companionapp.core.requests.qtil.SetHandsetServiceRequest;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class HandsetServiceRepositoryImpl extends HandsetServiceRepositoryData {

    private final HandsetServiceSubscriber mSubscriber = new HandsetServiceSubscriber() {
        @Override
        public void onInfo(HandsetServiceInfo info, Object update) {
            if (info == HandsetServiceInfo.MULTIPOINT_TYPE && update instanceof MultipointType) {
                MultipointType type = (MultipointType) update;
                updateMultipointType(type);
            }
        }

        @Override
        public void onError(HandsetServiceInfo info, Reason reason) {

        }
    };

    @Inject
    public HandsetServiceRepositoryImpl() {
    }

    public void init() {
        PublicationManager publicationManager = GaiaClientService.getPublicationManager();
        publicationManager.subscribe(mSubscriber);
    }

    @Override
    public void setMultipointType(Context context, MultipointType type) {
        SetHandsetServiceRequest request = new SetHandsetServiceRequest(HandsetServiceInfo.MULTIPOINT_TYPE, type);
        GaiaClientService.getRequestManager().execute(context, request);
    }

}
