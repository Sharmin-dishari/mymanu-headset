/*
 * ************************************************************************************************
 * * © 2020 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.gaia.qtil.plugins;

import com.mymanu.companionapp.core.data.LogInfo;

import java.io.File;

public interface DebugPlugin {

    void fetchLogInfo(LogInfo info);

    void downloadLogs(File file);

    void cancelDownload();

    void initTransferSession();
}
