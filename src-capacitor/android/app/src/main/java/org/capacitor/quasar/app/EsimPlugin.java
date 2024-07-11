package org.capacitor.quasar.app;

import android.content.Intent;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "Esim")
public class EsimPlugin extends Plugin {

  @PluginMethod()
  public void openEsimActivity(PluginCall call) {
    String value = call.getString("value");

    // Create an intent to start the new activity
    Intent intent = new Intent(getActivity(), EsimManagerActivity.class);
    intent.putExtra("value", value); // Pass any data you need to the new activity

    // Start the new activity
    getActivity().startActivity(intent);

    JSObject ret = new JSObject();
    ret.put("value", value);
    call.resolve(ret);
  }
}
