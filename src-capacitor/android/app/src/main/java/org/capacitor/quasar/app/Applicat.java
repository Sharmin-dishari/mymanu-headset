package org.capacitor.quasar.app;

import android.app.Application;

import com.kfree.cmd.DeviceBleEx;

public class Applicat extends Application {
    private final static String TAG = "Applicat";

    public static DeviceBleEx gDevBle = null;

    @Override
    public void onCreate() {
        super.onCreate();

        com.kfree.Log.Config(this,"---bluetoothheadsetdemo---");
    }

}

