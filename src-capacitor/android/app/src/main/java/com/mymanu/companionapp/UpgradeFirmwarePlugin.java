package com.mymanu.companionapp;

import android.content.Intent;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.mymanu.companionapp.ui.UpdateActivity;

@CapacitorPlugin(name = "UpgradeFirmware")
public class UpgradeFirmwarePlugin extends Plugin {

  @PluginMethod()
  public void openFirmwareActivity(PluginCall call) {
    String value = call.getString("value");

    // Create an intent to start the new activity
    Intent intent = new Intent(getActivity(), UpdateActivity.class);
    intent.putExtra("value", value); // Pass any data you need to the new activity

    // Start the new activity
    getActivity().startActivity(intent);

    JSObject ret = new JSObject();
    ret.put("value", value);
    call.resolve(ret);
  }
}
