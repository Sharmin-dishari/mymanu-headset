/*
 * ************************************************************************************************
 * * © 2020 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.gaia.qtil.plugins;

import com.mymanu.companionapp.core.data.DeviceInfo;
import com.mymanu.companionapp.core.data.FlowControlInfo;
import com.mymanu.companionapp.core.data.SizeInfo;
import com.mymanu.companionapp.core.gaia.qtil.data.QTILFeature;
import com.mymanu.companionapp.core.gaia.qtil.plugins.v3.DataTransferListener;
import com.mymanu.companionapp.core.publications.qtil.publishers.DeviceInformationPublisher;

public interface BasicPlugin {

    void fetchDeviceInfo(DeviceInfo info);

    boolean fetchSizeInfo(SizeInfo info);

    boolean fetchFlowControlInfo(FlowControlInfo info);

    boolean setSize(SizeInfo info, long value);

    boolean setFlowControl(FlowControlInfo info, boolean enabled);

    void registerDataTransferListener(int session, DataTransferListener listener);

    void unregisterDataTransferListener(int session);

    boolean startDataTransfer(int session);

    boolean transferData(int session, long offset, long size);

    void registerNotification(QTILFeature feature, PluginStarter pluginStarter);

    void cancelNotification(QTILFeature feature);

    // to force the BasicPlugin to implement DeviceInformationPublisher
    // this is unused
    DeviceInformationPublisher getDeviceInformationPublisher();

}
