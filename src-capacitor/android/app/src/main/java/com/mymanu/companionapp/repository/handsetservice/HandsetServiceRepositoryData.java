/*
 * ************************************************************************************************
 * * © 2021-2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.repository.handsetservice;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.mymanu.companionapp.core.gaia.qtil.data.MultipointType;
import com.mymanu.companionapp.ui.common.StickyLiveData;

import javax.inject.Singleton;

@Singleton
public abstract class HandsetServiceRepositoryData implements HandsetServiceRepository {

    private final StickyLiveData<MultipointType> multipointTypeLiveData = new StickyLiveData<>();

    @NonNull
    @Override
    public LiveData<MultipointType> getMultipointTypeLiveData() {
        return multipointTypeLiveData;
    }

    @Override
    public void reset() {
        multipointTypeLiveData.setValue(null);
    }

    protected void updateMultipointType(MultipointType type) {
        multipointTypeLiveData.postValue(type);
    }

}
