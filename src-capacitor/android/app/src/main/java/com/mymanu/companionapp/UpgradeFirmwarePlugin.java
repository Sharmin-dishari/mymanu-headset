package com.mymanu.companionapp;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "UpgradeFirmware")
public class UpgradeFirmwarePlugin extends Plugin {

  @PluginMethod()
  public void openFirmwareActivity(PluginCall call) {
    String value = call.getString("value");

    // Create an intent to start the new activity
//    Intent intent = new Intent(getActivity(), EsimManagerActivity.class);
//    intent.putExtra("value", value); // Pass any data you need to the new activity
//
//    // Start the new activity
//    getActivity().startActivity(intent);
//
//    JSObject ret = new JSObject();
//    ret.put("value", value);
//    call.resolve(ret);
  }
}
