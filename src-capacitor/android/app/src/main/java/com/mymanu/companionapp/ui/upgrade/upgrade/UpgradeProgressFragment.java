/*
 * ************************************************************************************************
 * * © 2020, 2023 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.       *
 * ************************************************************************************************
 */

package com.mymanu.companionapp.ui.upgrade.upgrade;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mymanu.companionapp.R;
import com.mymanu.companionapp.ui.Navigator;
import com.mymanu.companionapp.ui.common.events.NavigationEvent;
import com.mymanu.companionapp.ui.common.events.NavigationEventType;
import com.mymanu.companionapp.ui.common.progress.ProgressFragment;
import com.mymanu.companionapp.ui.common.progress.ProgressFragmentViewData;
import com.mymanu.companionapp.libraries.upgrade.data.ConfirmationOptions;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UpgradeProgressFragment
        extends ProgressFragment<UpgradeDialog, ConfirmationOptions, UpgradeProgressViewModel> {

    @Override
    protected UpgradeProgressViewModel initViewModel(Fragment fragment) {
        return new ViewModelProvider(this).get(UpgradeProgressViewModel.class);
    }

    @Override
    protected ProgressFragmentViewData getProgressFragmentViewData() {
        return new ProgressFragmentViewData(getString(R.string.button_abort), getString(R.string.button_done));
    }

    @Override
    protected void onEvent(NavigationEvent event) {
        if (event != null && event.getType().equals(NavigationEventType.NAVIGATE_BACK)) {
            navigateBack();
        }
    }

    private void navigateBack() {
        Navigator.navigate(getRoot(), UpgradeProgressFragmentDirections.back());
    }
}
