/*
 * ************************************************************************************************
 * * © 2020 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.core.gaia.core.version;

import com.mymanu.companionapp.core.data.Reason;

public interface V2ApiVersionFetcherListener {

    void onVersion(V2ApiVersion version);

    void onError(Reason reason);

}
