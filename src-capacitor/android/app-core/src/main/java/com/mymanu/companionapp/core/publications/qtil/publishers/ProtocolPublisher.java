/*
 * ************************************************************************************************
 * * © 2020-2021, 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.  *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.publications.qtil.publishers;

import androidx.annotation.NonNull;

import com.mymanu.companionapp.core.data.FlowControlInfo;
import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.data.SizeInfo;
import com.mymanu.companionapp.core.gaia.qtil.data.basic.ProtocolInfo;
import com.mymanu.companionapp.core.publications.core.Publisher;
import com.mymanu.companionapp.core.publications.core.Subscription;
import com.mymanu.companionapp.core.publications.qtil.CoreSubscription;
import com.mymanu.companionapp.core.publications.qtil.subscribers.ProtocolSubscriber;

public class ProtocolPublisher extends Publisher<ProtocolSubscriber> {

    @Override
    public Subscription getSubscription() {
        return CoreSubscription.TRANSPORT_INFORMATION;
    }

    public void publishProtocolVersion(long version) {
        forEachSubscriber(subscriber -> subscriber.onProtocolVersion(version));
    }

    public void publishSizeInfo(SizeInfo info, int size) {
        forEachSubscriber(subscriber -> subscriber.onSizeInfo(info, size));
    }

    public void publishFlowControlInfo(FlowControlInfo info, boolean enabled) {
        forEachSubscriber(subscriber -> subscriber.onFlowControlInfo(info, enabled));
    }

    public void publishError(@NonNull ProtocolInfo protocolInfo, Reason reason) {
        Object info = new Object(); // a null value represents protocol version

        switch (protocolInfo) {
            case MAX_TX_PACKET_SIZE:
            case OPTIMUM_TX_PACKET_SIZE:
            case MAX_RX_PACKET_SIZE:
            case OPTIMUM_RX_PACKET_SIZE:
                info = SizeInfo.valueOf(protocolInfo);
                break;

            case TX_FLOW_CONTROL:
            case RX_FLOW_CONTROL:
                info = FlowControlInfo.valueOf(protocolInfo);
                break;

            case PROTOCOL_VERSION:
                info = null;
                break;
        }

        Object finalInfo = info;
        forEachSubscriber(subscriber -> subscriber.onError(finalInfo, reason));
    }

}