/*
 * ************************************************************************************************
 * * © 2021-2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.ui.settings.audiocuration.demo;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;

import com.mymanu.companionapp.ui.settings.audiocuration.demo.AudioCurationDemoSettings;
import com.mymanu.companionapp.ui.settings.audiocuration.demo.AudioCurationDemoViewData;
import com.mymanu.companionapp.ui.settings.audiocuration.demo.DiscreteSliderPreference;
import com.mymanu.companionapp.ui.settings.audiocuration.demo.LeftRightBalancePreference;
import com.mymanu.companionapp.ui.settings.audiocuration.demo.SliderPreference;
import com.mymanu.companionapp.ui.settings.audiocuration.demo.TouchpadIndicatorsPreference;
import com.mymanu.companionapp.ui.settings.common.SettingsBinding;

public class AudioCurationDemoBinding extends SettingsBinding<AudioCurationDemoSettings, AudioCurationDemoViewData> {

    public AudioCurationDemoBinding(PreferenceManager preferenceManager, View view) {
        super(preferenceManager, view);
    }

    @Override
    protected void observeCustomSettings(@NonNull AudioCurationDemoViewData viewData,
                                         @NonNull AudioCurationDemoSettings setting, @NonNull Preference preference,
                                         LifecycleOwner owner) {
        if (preference instanceof ModesPreference) {
            ModesPreference modesPreference = (ModesPreference) preference;
            viewData.observeModesList(setting, owner, modesPreference::updateList);
            viewData.observeMode(setting, owner, modesPreference::setSelected);
        }
        if (preference instanceof GainPreference) {
            GainPreference gainPreference = (GainPreference) preference;
            viewData.observeGain(setting, owner, gainPreference::setData);
        }
        if (preference instanceof SliderPreference) {
            SliderPreference sliderPreference = (SliderPreference) preference;
            viewData.observeProgress(setting, owner, sliderPreference::setData);
        }
        if (preference instanceof DiscreteSliderPreference) {
            DiscreteSliderPreference discreteSliderPreference = (DiscreteSliderPreference) preference;
            viewData.observeDiscreteSliderMinValue(setting, owner, discreteSliderPreference::setMinValue);
            viewData.observeDiscreteSliderMaxValue(setting, owner, discreteSliderPreference::setMaxValue);
            viewData.observeDiscreteSliderValue(setting, owner, discreteSliderPreference::setValue);
            viewData.observeDiscreteSliderStepSize(setting, owner, discreteSliderPreference::setStepSize);
            viewData.observeDiscreteSliderLabel(setting, owner, discreteSliderPreference::setLabel);
            viewData.observeDiscreteSliderLabelFormatter(setting, owner,
                                                         discreteSliderPreference::setLabelFormatter);
        }
        if (preference instanceof LeftRightBalancePreference) {
            LeftRightBalancePreference leftRightBalancePreference = (LeftRightBalancePreference) preference;
            viewData.observeLeftRightBalanceValue(setting, owner, leftRightBalancePreference::setValue);
            viewData.observeLeftRightBalanceLabel(setting, owner, leftRightBalancePreference::setLabel);
            viewData.observeLeftRightBalanceLabelFormatter(setting, owner,
                                                           leftRightBalancePreference::setLabelFormatter);
        }
        if (preference instanceof TouchpadIndicatorsPreference) {
            TouchpadIndicatorsPreference touchpadPreference = (TouchpadIndicatorsPreference) preference;
            viewData.observeWindNoiseReductionLeftTouchpadData(setting, owner, touchpadPreference::setLeftTouchpadData);
            viewData.observeWindNoiseReductionRightTouchpadData(setting, owner, touchpadPreference::setRightTouchpadData);
        }
    }
}
