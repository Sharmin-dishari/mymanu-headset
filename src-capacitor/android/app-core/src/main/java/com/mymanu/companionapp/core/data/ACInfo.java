/*
 * ************************************************************************************************
 * * © 2020-2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.data;

import com.mymanu.companionapp.core.gaia.qtil.data.audiocuration.AdaptationState;
import com.mymanu.companionapp.core.gaia.qtil.data.audiocuration.AutoTransparencyReleaseTime;
import com.mymanu.companionapp.core.gaia.qtil.data.audiocuration.DemoState;
import com.mymanu.companionapp.core.gaia.qtil.data.audiocuration.FeatureState;
import com.mymanu.companionapp.core.gaia.qtil.data.audiocuration.FilterTopology;
import com.mymanu.companionapp.core.gaia.qtil.data.audiocuration.Gain;
import com.mymanu.companionapp.core.gaia.qtil.data.audiocuration.GainReduction;
import com.mymanu.companionapp.core.gaia.qtil.data.audiocuration.LeakthroughGain;
import com.mymanu.companionapp.core.gaia.qtil.data.audiocuration.LeakthroughGainConfiguration;
import com.mymanu.companionapp.core.gaia.qtil.data.audiocuration.LeakthroughGainStep;
import com.mymanu.companionapp.core.gaia.qtil.data.audiocuration.LeftRightBalance;
import com.mymanu.companionapp.core.gaia.qtil.data.audiocuration.Mode;
import com.mymanu.companionapp.core.gaia.qtil.data.audiocuration.NoiseIdCategory;
import com.mymanu.companionapp.core.gaia.qtil.data.audiocuration.ScenarioConfiguration;
import com.mymanu.companionapp.core.gaia.qtil.data.audiocuration.State;
import com.mymanu.companionapp.core.gaia.qtil.data.audiocuration.Support;
import com.mymanu.companionapp.core.gaia.qtil.data.audiocuration.ToggleConfiguration;
import com.mymanu.companionapp.core.gaia.qtil.data.audiocuration.WindNoiseReduction;

public enum ACInfo {
    /**
     * Information of type {@link FeatureState}.
     */
    AC_FEATURE_STATE,
    /**
     * Information of type {@link Integer}.
     */
    MODES_COUNT,
    /**
     * Information of type {@link Mode}.
     */
    MODE,
    /**
     * Information of type {@link Gain}.
     * GET and NOTIFIED only.
     */
    FEED_FORWARD_GAIN,
    /**
     * Information of type {@link LeakthroughGain}.
     * SET only.
     */
    LEAKTHROUGH_GAIN,
    /**
     * Information of type {@link androidx.core.util.Pair}<{@link Integer}, .
     */
    TOGGLES_COUNT,
    /**
     * Information of type {@link ToggleConfiguration}.
     */
    TOGGLE_CONFIGURATION,
    /**
     * Information of type {@link ScenarioConfiguration}.
     */
    SCENARIO_CONFIGURATION,
    /**
     * Information of type {@link Support}.
     */
    DEMO_SUPPORT,
    /**
     * {@link DemoState}.
     */
    DEMO_STATE,
    /**
     * Information of type {@link AdaptationState}.
     */
    ADAPTATION_STATE,
    /**
     * Information of type {@link LeakthroughGainConfiguration}.
     */
    LEAKTHROUGH_GAIN_CONFIGURATION,
    /**
     * Information of type {@link LeakthroughGainStep}.
     */
    LEAKTHROUGH_GAIN_STEP,
    /**
     * Information of type {@link LeftRightBalance}.
     */
    LEFT_RIGHT_BALANCE,
    /**
     * Information of type {@link Support}.
     */
    WIND_NOISE_DETECTION_SUPPORT,
    /**
     * Information of type {@link State}.
     */
    WIND_NOISE_DETECTION_STATE,
    /**
     * Information of type {@link WindNoiseReduction}.
     */
    WIND_NOISE_REDUCTION,
    /**
     * Information of type {@link Support}.
     */
    AUTO_TRANSPARENCY_SUPPORT,
    /**
     * Information of type {@link State}.
     */
    AUTO_TRANSPARENCY_STATE,
    /**
     * Information of type {@link AutoTransparencyReleaseTime}.
     */
    AUTO_TRANSPARENCY_RELEASE_TIME,
    /**
     * Information of type {@link Support}.
     */
    HOWLING_DETECTION_SUPPORT,
    /**
     * Information of type {@link State}.
     */
    HOWLING_DETECTION_STATE,
    /**
     * Information of type {@link Gain}.
     * GET and NOTIFIED only.
     */
    FEEDBACK_GAIN,
    /**
     * Information of type {@link Support}.
     */
    NOISE_ID_SUPPORT,
    /**
     * Information of type {@link State}.
     */
    NOISE_ID_STATE,
    /**
     * Information of type {@link NoiseIdCategory}.
     */
    NOISE_ID_CATEGORY,
    /**
     * Information of type {@link Support}.
     * GET only.
     */
    ADVERSE_ACOUSTIC_SUPPORT,
    /**
     * Information of type {@link State}.
     * GET, SET and NOTIFIED.
     */
    ADVERSE_ACOUSTIC_STATE,
    /**
     * Information of type {@link GainReduction}.
     * NOTIFIED only.
     */
    ADVERSE_ACOUSTIC_GAIN_REDUCTION,
    /**
     * Information of type {@link GainReduction}.
     * NOTIFIED only.
     */
    HOWLING_CONTROL_GAIN_REDUCTION,
    /**
     * Information of type {@link FilterTopology}.
     * GET only.
     */
    FILTER_TOPOLOGY,
}
