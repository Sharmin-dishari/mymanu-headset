/*
 * ************************************************************************************************
 * * © 2021-2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.ui.settings.audiocuration.demo;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.mymanu.companionapp.ui.common.StickyLiveData;
import com.mymanu.companionapp.ui.settings.audiocuration.demo.TouchpadViewData;
import com.mymanu.companionapp.ui.settings.common.data.SettingData;

public class TouchpadIndicatorsSettingData extends SettingData {

    private final StickyLiveData<TouchpadViewData> leftTouchpadData = new StickyLiveData<>();

    private final StickyLiveData<TouchpadViewData> rightTouchpadData = new StickyLiveData<>();

    public void observeLeftTouchpadData(LifecycleOwner owner, Observer<TouchpadViewData> observer) {
        leftTouchpadData.observe(owner, observer);
    }

    public void observeRightTouchpadData(LifecycleOwner owner, Observer<TouchpadViewData> observer) {
        rightTouchpadData.observe(owner, observer);
    }

    public void setLeftTouchpadData(TouchpadViewData data) {
        this.leftTouchpadData.postValue(data);
    }

    public void setRightTouchpadData(TouchpadViewData data) {
        this.rightTouchpadData.postValue(data);
    }
}
