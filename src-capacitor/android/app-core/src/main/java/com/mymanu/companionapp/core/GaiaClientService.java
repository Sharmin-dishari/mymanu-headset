/*
 * ************************************************************************************************
 * * © 2020-2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.        *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.IntentFilter;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

import com.mymanu.companionapp.core.bluetooth.BluetoothStateReceiver;
import com.mymanu.companionapp.core.bluetooth.TransportManager;
import com.mymanu.companionapp.core.bluetooth.TransportManagerWrapper;
import com.mymanu.companionapp.core.bluetooth.reconnection.ReconnectionDelegate;
import com.mymanu.companionapp.core.bluetooth.reconnection.ReconnectionObserver;
import com.mymanu.companionapp.core.gaia.GaiaManager;
import com.mymanu.companionapp.core.gaia.GaiaManagerWrapper;
import com.mymanu.companionapp.core.gaia.qtil.QTILManager;
import com.mymanu.companionapp.core.gaia.qtil.QTILManagerWrapper;
import com.mymanu.companionapp.core.publications.PublicationManager;
import com.mymanu.companionapp.core.publications.PublicationManagerWrapper;
import com.mymanu.companionapp.core.requests.RequestManager;
import com.mymanu.companionapp.core.requests.RequestManagerWrapper;
import com.mymanu.companionapp.core.tasks.TaskManager;
import com.mymanu.companionapp.core.tasks.TaskManagerWrapper;
import com.mymanu.companionapp.core.utils.BluetoothUtils;
import com.mymanu.companionapp.core.gaia.core.Vendor;
import com.mymanu.companionapp.core.publications.core.Publisher;
import com.mymanu.companionapp.core.publications.core.Subscriber;
import com.mymanu.companionapp.core.requests.core.Request;

/**
 * <p>This is the entry point of the core library: it provides access to the different APIs.</p>
 * <p>Call {@link #prepare(Context)} to initialise this service from the Main Thread of the
 * application.
 * <p>Once initialised, use the static getters to access the features of the core library.</p>
 * <p>Once this singleton is not necessary anymore - for instance when the application
 * terminates - it can be disposed of by calling {@link #release(Context)} from the Main Thread.</p>
 * <p>This static singleton initialises all the different managers that can be used outside of the
 * core library as singletons.</p>
 */
public final class GaiaClientService {

    /**
     * A static instance to use this as a singleton.
     */
    private static GaiaClientService sInstance;

    // all the managers are implemented using wrappers for visibility purposes.
    //  This hides public methods from other scopes - a listener for instance.
    private final PublicationManagerWrapper mPublicationManager;
    private final GaiaManagerWrapper mGaiaManager;
    private final QTILManagerWrapper mQtilManager;
    private final TransportManagerWrapper mTransportManager;
    private final RequestManager mRequestManager;
    private final TaskManagerWrapper mTaskManager;
    private final BluetoothStateReceiver mBluetoothStateReceiver;
    private final ReconnectionDelegate mReconnectionDelegate;

    /**
     * Must be called before any other method to initialise the singleton.
     *
     * @param context
     *         Provides the context of the application.
     */
    @MainThread
    public static void prepare(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new GaiaClientService(context);
        }
    }

    /**
     * Must be called when the singleton can be disposed of or when the application terminates.
     *
     * @param context
     *         Provides the context of the application.
     */
    @MainThread
    public static void release(@NonNull Context context) {
        if (sInstance != null) {
            sInstance.releaseAll(context);
            sInstance = null;
        }
    }

    /**
     * To get the PublicationManager in order to register/unregister
     * {@link Publisher} and subscribe
     * /unsubscribe {@link Subscriber}.
     *
     * @throws RuntimeException
     *         Throws a RuntimeException if this is called prior to
     *         {@link #prepare(Context)} being called.
     */
    @NonNull
    public static PublicationManager getPublicationManager() {
        if (sInstance == null) {
            throw new RuntimeException("GaiaClientService.getPublicationManager: must call " +
                                               "GaiaClientService.prepare() first");
        }

        return sInstance.mPublicationManager;
    }

    /**
     * To get the RequestManager in order to execute
     * {@link Request}}.
     *
     * @throws RuntimeException
     *         Throws a RuntimeException if this is called prior to
     *         {@link #prepare(Context)} being called.
     */
    @NonNull
    public static RequestManager getRequestManager() {
        if (sInstance == null) {
            throw new RuntimeException("GaiaClientService.getRequestManager: must call " +
                                               "GaiaClientService.prepare() first");
        }

        return sInstance.mRequestManager;
    }

    /**
     * To get the GaiaManager in order to register
     * {@link Vendor}.
     *
     * @throws RuntimeException
     *         Throws a RuntimeException if this is called prior to
     *         {@link #prepare(Context)} being called.
     */
    @NonNull
    public static GaiaManager getGaiaManager() {
        if (sInstance == null) {
            throw new RuntimeException("GaiaClientService.getGaiaManager: must call " +
                                               "GaiaClientService.prepare() first");
        }

        return sInstance.mGaiaManager;
    }

    /**
     * To get the TransportManager in order to request connections and disconnections to a device.
     *
     * @throws RuntimeException
     *         Throws a RuntimeException if this is called prior to
     *         {@link #prepare(Context)} being called.
     */
    @NonNull
    public static TransportManager getTransportManager() {
        if (sInstance == null) {
            throw new RuntimeException("GaiaClientService.getTransportManager: must call " +
                                               "GaiaClientService.prepare() first");
        }

        return sInstance.mTransportManager;
    }

    /**
     * To get the TaskManager in order to execute and schedule tasks.
     *
     * @throws RuntimeException
     *         Throws a RuntimeException if this is called prior to
     *         {@link #prepare(Context)} being called.
     */

    @NonNull
    public static TaskManager getTaskManager() {
        if (sInstance == null) {
            throw new RuntimeException("GaiaClientService.getTaskManager: must call " +
                                               "GaiaClientService.prepare() first");
        }

        return sInstance.mTaskManager;
    }

    /**
     * To get the QTILManager in order to fetch QTIL related data or execute QTIL related tasks -
     * such as a device upgrade.
     *
     * @throws RuntimeException
     *         Throws a RuntimeException if this is called prior to
     *         {@link #prepare(Context)} being called.
     */
    @NonNull
    public static QTILManager getQtilManager() {
        if (sInstance == null) {
            throw new RuntimeException("GaiaClientService.getQtilManager: must call " +
                                               "GaiaClientService.prepare() first");
        }

        return sInstance.mQtilManager;
    }

    /**
     * To get the ReconnectionObserver in order to enable/disable the reconnection Runnable.
     *
     * @throws RuntimeException
     *         Throws a RuntimeException if this is called prior to
     *         {@link #prepare(Context)} being called.
     */
    @NonNull
    public static ReconnectionObserver getReconnectionObserver() {
        if (sInstance == null) {
            throw new RuntimeException("GaiaClientService.getReconnectionObserver: must call " +
                                               "GaiaClientService.prepare() first");
        }

        return sInstance.mReconnectionDelegate;
    }

    /**
     * private constructor for singleton.
     * It initialises all the managers.
     */
    private GaiaClientService(@NonNull Context context) {
        mTaskManager = new TaskManagerWrapper();
        mRequestManager = new RequestManagerWrapper();
        mPublicationManager = new PublicationManagerWrapper();
        BluetoothAdapter adapter = BluetoothUtils.getBluetoothAdapter(context);
        mGaiaManager = new GaiaManagerWrapper(mPublicationManager);
        mQtilManager = new QTILManagerWrapper(mGaiaManager, mPublicationManager);
        mTransportManager = new TransportManagerWrapper(mPublicationManager);

        mBluetoothStateReceiver = new BluetoothStateReceiver(mPublicationManager);
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        context.registerReceiver(mBluetoothStateReceiver, filter);

        mReconnectionDelegate = new ReconnectionDelegate(mTaskManager, mPublicationManager,
                                                         mTransportManager, adapter);
    }

    /**
     * Release all the managers by releasing their resources.
     */
    private void releaseAll(@NonNull Context context) {
        context.unregisterReceiver(mBluetoothStateReceiver);
        mReconnectionDelegate.release();
        mPublicationManager.release();
        mGaiaManager.release();
        mTransportManager.release();
        mTaskManager.release();
        mQtilManager.release();
    }

}
