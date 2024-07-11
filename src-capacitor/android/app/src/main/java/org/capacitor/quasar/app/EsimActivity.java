package org.capacitor.quasar.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class EsimActivity extends AppCompatActivity  implements View.OnClickListener {
  private String TAG = "bluetoothheadsetdemo:EsimActivity";
  private TextView mtv_show;
  private static final int REQUEST_BLUETOOTH_PERMISSIONS = 1;

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esim);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

      // Retrieve the value passed from the plugin
//      String value = getIntent().getStringExtra("value");
//
//      // Use the value in your activity
//      TextView textView = findViewById(R.id.textView);
//      textView.setText(value);


      mtv_show = findViewById(R.id.tv_show);

    if (!checkBluetoothPermissions()) {
      requestBluetoothPermissions();
    } else {
      // Proceed with Bluetooth operation
    }

  }

  public boolean checkBluetoothPermissions() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
      // Android 13 and above
      return ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_ADVERTISE) == PackageManager.PERMISSION_GRANTED;
    } else {
      // Android 12 and below
      return ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
  }

  public void requestBluetoothPermissions() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
      // Android 13 and above
      ActivityCompat.requestPermissions(this,
        new String[]{android.Manifest.permission.BLUETOOTH_SCAN, android.Manifest.permission.BLUETOOTH_CONNECT, android.Manifest.permission.BLUETOOTH_ADVERTISE, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.CAMERA},
        REQUEST_BLUETOOTH_PERMISSIONS);
    } else {
      // Android 12 and below
      ActivityCompat.requestPermissions(this,
        new String[]{android.Manifest.permission.BLUETOOTH, android.Manifest.permission.BLUETOOTH_ADMIN, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.CAMERA},
        REQUEST_BLUETOOTH_PERMISSIONS);
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == REQUEST_BLUETOOTH_PERMISSIONS) {
      boolean allGranted = true;
      for (int result : grantResults) {
        if (result != PackageManager.PERMISSION_GRANTED) {
          allGranted = false;
          break;
        }
      }
      if (allGranted) {
        // Permissions granted, proceed with Bluetooth operation
      } else {
        // Permissions denied, show a message to the user
      }
    }
  }


  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      int id = item.getItemId();
      if(id == android.R.id.home){
        finish();
      }
    return super.onOptionsItemSelected(item);
  }


  @Override
  public void onClick(View view) {
    int id = view.getId();
    if (id == R.id.btn_esim) {
      Intent esim = new Intent(this, EsimManagerActivity.class);
      startActivity(esim);
    } else if (id == R.id.btn_btCmd) {
      Intent btb = new Intent(this, BluetoothButton.class);
      startActivity(btb);
    }
  }

}
