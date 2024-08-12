/*
 * ************************************************************************************************
 * * © 2020 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.repository.logs;

import com.mymanu.companionapp.core.data.DownloadLogsState;

public class LogsProgress {

    private final DownloadLogsState state;

    private final double progress;

    public LogsProgress(DownloadLogsState state, double progress) {
        this.state = state;
        this.progress = progress;
    }

    public DownloadLogsState getState() {
        return state;
    }

    public double getProgress() {
        return progress;
    }
}
