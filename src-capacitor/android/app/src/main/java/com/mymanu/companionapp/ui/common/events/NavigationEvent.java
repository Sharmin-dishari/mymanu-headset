/*
 * ************************************************************************************************
 * * © 2020 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.ui.common.events;

public class NavigationEvent {

    private final NavigationEventType mType;

    public NavigationEvent(NavigationEventType type) {
        this.mType = type;
    }

    public NavigationEventType getType() {
        return mType;
    }

}
