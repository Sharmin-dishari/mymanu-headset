/*
 * ************************************************************************************************
 * * © 2020, 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.       *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.gaia;

import com.mymanu.companionapp.core.gaia.core.sending.GaiaSender;
import com.mymanu.companionapp.core.gaia.core.Vendor;
import com.mymanu.companionapp.core.gaia.core.transport.GaiaReader;
import com.mymanu.companionapp.core.publications.PublicationManager;

import androidx.annotation.NonNull;

public final class GaiaManagerWrapper implements GaiaManager {

    private final GaiaManagerImpl mManager;

    public GaiaManagerWrapper(@NonNull PublicationManager publicationManager) {
        this.mManager = new GaiaManagerImpl(publicationManager);
    }

    @Override
    public void registerVendor(@NonNull Vendor vendor) {
        mManager.getVendorHandler().addVendor(vendor);
    }

    @Override
    public GaiaSender getSender() {
        return mManager.getGaiaSender();
    }

    @Override
    public GaiaReader.Listener getReaderListener() {
        return mManager.getReaderListener();
    }

    public void release() {
        mManager.release();
    }
}
