package com.mymanu.companionapp;

import android.os.Bundle;

import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    registerPlugin(EsimPlugin.class);
    registerPlugin(UpgradeFirmwarePlugin.class);
    super.onCreate(savedInstanceState);
  }

  @Override
  public void onResume() {
    super.onResume();
    // Handle the transition from background to foreground
  }
}
