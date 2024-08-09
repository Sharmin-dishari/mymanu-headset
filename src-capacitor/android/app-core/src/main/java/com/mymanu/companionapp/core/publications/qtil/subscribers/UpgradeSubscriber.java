/*
 * ************************************************************************************************
 * * © 2020 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.subscribers;

import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;
import com.mymanu.companionapp.core.upgrade.data.ChunkSizeType;
import com.mymanu.companionapp.core.upgrade.data.UpgradeFail;
import com.mymanu.companionapp.core.upgrade.data.UpgradeProgress;
import com.mymanu.companionapp.core.publications.core.Subscriber;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.libraries.upgrade.messages.UpgradeAlert;

import androidx.annotation.NonNull;

public interface UpgradeSubscriber extends Subscriber {

    @NonNull
    @Override
    default Subscription getSubscription() {
        return CoreSubscription.UPGRADE;
    }

    void onProgress(UpgradeProgress progress);

    void onError(UpgradeFail error);

    void onChunkSize(ChunkSizeType type, int size);

    void onAlert(UpgradeAlert alert, boolean raised);
}
