/*
 * ************************************************************************************************
 * * © 2020-2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.repository.upgrade;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.lifecycle.LiveData;

import com.mymanu.companionapp.core.upgrade.data.UpgradeConfirmation;
import com.mymanu.companionapp.core.upgrade.data.UpgradeFail;
import com.mymanu.companionapp.core.upgrade.data.UpgradeProgress;
import com.mymanu.companionapp.repository.Result;
import com.mymanu.companionapp.libraries.upgrade.data.ConfirmationOptions;
import com.mymanu.companionapp.libraries.upgrade.messages.UpgradeAlert;

public interface UpgradeRepository {

    void init();

    @NonNull
    LiveData<Result<UpgradeProgress, UpgradeFail>> getUpgradeLiveData();

    @NonNull
    LiveData<Integer> getSizeLiveData(SizeType type);

    @NonNull
    LiveData<Pair<UpgradeAlert, Boolean>> getAlertsLiveData();

    int getSize(SizeType type);

    void startUpgrade(Context context, Uri file, UpgradeOptions options);

    void abortUpgrade(Context context);

    void confirmUpgrade(Context context, UpgradeConfirmation confirmation, @NonNull ConfirmationOptions option);

    void reset();

}
