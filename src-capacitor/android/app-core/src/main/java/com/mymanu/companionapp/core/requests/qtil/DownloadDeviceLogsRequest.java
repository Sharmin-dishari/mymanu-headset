/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.requests.qtil;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.plugins.DebugPlugin;
import com.mymanu.companionapp.core.requests.core.Request;
import com.mymanu.companionapp.core.requests.core.RequestListener;
import com.mymanu.companionapp.core.GaiaClientService;

import java.io.File;

public class DownloadDeviceLogsRequest extends Request<Void, Void, Reason> {

    private final File file;

    /**
     * @param file the application must have write permissions on the provided file.
     */
    public DownloadDeviceLogsRequest(@NonNull RequestListener<Void, Void, Reason> listener, File file) {
        super(listener);
        this.file = file;
    }

    @Override
    public void run(@Nullable Context context) {
        if (file == null) {
            onError(Reason.FILE_CREATION_FAILED);
            return;
        }

        DebugPlugin plugin = GaiaClientService.getQtilManager().getDebugPlugin();
        if (plugin != null) {
            plugin.downloadLogs(file);
            onComplete(null);
        }
        else {
            onError(Reason.NOT_SUPPORTED);
        }
    }

    @Override
    protected void onEnd() {
        // no state to clear
    }
}
