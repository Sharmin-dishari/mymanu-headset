/*
 * ************************************************************************************************
 * * © 2020 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.subscribers;

import com.mymanu.companionapp.core.data.FlowControlInfo;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.data.SizeInfo;
import com.mymanu.companionapp.core.publications.core.Subscriber;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;

import androidx.annotation.NonNull;

public interface ProtocolSubscriber extends Subscriber {

    @NonNull
    default Subscription getSubscription() {
        return CoreSubscription.TRANSPORT_INFORMATION;
    }

    default void onProtocolVersion(long version) {
    }

    default void onSizeInfo(SizeInfo info, int size) {
    }

    default void onFlowControlInfo(FlowControlInfo info, boolean enabled) {
    }

    /**
     * @param info
     *         instance of {@link SizeInfo}, {@link FlowControlInfo} or null if the error
     *         occurred for the protocol version information.
     */
    void onError(Object info, Reason reason);

}
