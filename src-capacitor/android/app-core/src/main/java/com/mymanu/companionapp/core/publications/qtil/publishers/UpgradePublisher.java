/*
 * ************************************************************************************************
 * * © 2020 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.publishers;

import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;
import com.mymanu.companionapp.core.upgrade.data.ChunkSizeType;
import com.mymanu.companionapp.core.upgrade.data.UpgradeFail;
import com.mymanu.companionapp.core.upgrade.data.UpgradeProgress;
import com.mymanu.companionapp.core.publications.core.Publisher;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.subscribers.UpgradeSubscriber;
import com.mymanu.companionapp.libraries.upgrade.messages.UpgradeAlert;

public class UpgradePublisher extends Publisher<UpgradeSubscriber> {

    @Override
    public Subscription getSubscription() {
        return CoreSubscription.UPGRADE;
    }

    public void publishProgress(UpgradeProgress progress) {
        forEachSubscriber(subscriber -> subscriber.onProgress(progress));
    }

    public void publishError(UpgradeFail error) {
        forEachSubscriber(subscriber -> subscriber.onError(error));
    }

    public void publishChunkSize(ChunkSizeType type, int size) {
        forEachSubscriber(subscriber -> subscriber.onChunkSize(type, size));
    }

    public void publishAlert(UpgradeAlert alert, boolean raised) {
        forEachSubscriber(subscriber -> subscriber.onAlert(alert, raised));
    }

}
