/*
 * ************************************************************************************************
 * * © 2020 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.ui.devicelogs;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.mymanu.companionapp.ui.common.events.NavigationEvent;
import com.mymanu.companionapp.ui.common.events.NavigationEventType;

public class ShareFileNavigationEvent extends NavigationEvent {

    @NonNull
    private final Uri uri;

    public ShareFileNavigationEvent(@NonNull Uri uri) {
        super(NavigationEventType.START_ACTIVITY);
        this.uri = uri;
    }

    @NonNull
    public Uri getUri() {
        return uri;
    }
}
