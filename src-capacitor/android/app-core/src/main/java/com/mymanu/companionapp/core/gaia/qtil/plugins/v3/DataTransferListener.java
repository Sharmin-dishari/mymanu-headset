/*
 * ************************************************************************************************
 * * © 2020-2021, 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.  *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.gaia.qtil.plugins.v3;

import com.mymanu.companionapp.core.data.Reason;
import com.mymanu.companionapp.core.gaia.qtil.data.basic.TransferSetupResponse;
import com.mymanu.companionapp.core.gaia.qtil.data.basic.TransferredData;

public interface DataTransferListener {

    void onSetUp(TransferSetupResponse setup);

    void onDataTransferred(TransferredData data);

    void onTransferError(int session, Reason reason);
}
