/*
 * ************************************************************************************************
 * * © 2021 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.ui.gestures.gestures;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mymanu.companionapp.core.gaia.qtil.data.gestures.Gesture;
import com.mymanu.companionapp.ui.common.ImageViewData;
import com.mymanu.companionapp.ui.common.ListAdapterItemData;

import java.util.Objects;

/**
 * This represents the data model of a gesture.
 */
public class GestureViewData implements ListAdapterItemData {

    @NonNull
    private final Gesture gesture;

    @NonNull
    private final String name;

    @NonNull
    private String summary;

    @Nullable
    private final ImageViewData image;

    public GestureViewData(@NonNull Gesture gesture, @NonNull String name, @Nullable ImageViewData image) {
        this.name = name;
        this.image = image;
        this.gesture = gesture;
    }

    public GestureViewData(GestureViewData origin) {
        this.gesture = origin.gesture;
        this.name = origin.name;
        this.summary = origin.summary;
        this.image = origin.image;
    }

    public void setSummary(@NonNull String summary) {
        this.summary = summary;
    }

    @NonNull
    public Gesture getGesture() {
        return gesture;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getSummary() {
        return summary;
    }

    @Nullable
    public ImageViewData getImage() {
        return image;
    }

    @Override // ListAdapterItemData
    public boolean isSameContent(ListAdapterItemData itemData) {
        return this.equals(itemData);
    }

    @Override // ListAdapterItemData
    public boolean isSameItem(ListAdapterItemData itemData) {
        if (this == itemData) {
            return true;
        }
        if (itemData == null || getClass() != itemData.getClass()) {
            return false;
        }
        GestureViewData that = (GestureViewData) itemData;
        return gesture.equals(that.gesture);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GestureViewData that = (GestureViewData) o;
        return name.equals(that.name) &&
                summary.equals(that.summary) &&
                Objects.equals(image, that.image) &&
                Objects.equals(gesture, that.gesture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, summary, image, gesture);
    }
}
