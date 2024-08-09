/*
 * ************************************************************************************************
 * * © 2020 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.gaia.qtil.plugins;

import com.mymanu.companionapp.core.data.Reason;

public class PluginStarter {

    private final Runnable startRunnable;

    private final OnErrorRunnable onErrorRunnable;

    public PluginStarter(Runnable startRunnable, OnErrorRunnable onErrorRunnable) {
        this.startRunnable = startRunnable;
        this.onErrorRunnable = onErrorRunnable;
    }

    public final void start() {
        if (startRunnable != null) {
            startRunnable.run();
        }
    }

    public final void onError(Reason reason) {
        if (onErrorRunnable != null) {
            onErrorRunnable.run(reason);
        }
    }

    public interface OnErrorRunnable {

        void run(Reason reason);
    }

}
