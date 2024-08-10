/*
 * ************************************************************************************************
 * * © 2021-2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.repository.earbudfit;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.data.EarbudsFitResults;
import com.mymanu.companionapp.core.gaia.qtil.data.FitTestState;
import com.mymanu.companionapp.repository.Result;

public interface EarbudFitRepository {

    void init();

    @NonNull
    LiveData<Result<EarbudsFitResults, Reason>> getFitResultsLiveData();

    void setFitTest(Context context, FitTestState state);

    void reset();
}
