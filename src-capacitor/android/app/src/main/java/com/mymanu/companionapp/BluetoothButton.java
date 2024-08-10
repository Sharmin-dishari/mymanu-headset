package com.mymanu.companionapp;

import static com.kfree.bt.BleClient2.BleScanActivity.EXTRA_BLE_DEV_ADD;
import static com.kfree.bt.BleClient2.BleScanActivity.SEARCH_SELECT_BLE_DEV;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.kfree.app.UtilsApp;
import com.kfree.bt.BleClient2.BleDevice;
import com.kfree.bt.BleClient2.BleDeviceCallbackImpNull;
import com.kfree.bt.BleClient2.BleScanActivity;
import com.kfree.bt.BleClient2.IBleDeviceCallback;
import com.kfree.cmd.CmdEnums;
import com.kfree.cmd.DeviceBle;
import com.kfree.cmd.DeviceBleEx;

public class BluetoothButton extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "BluetoothButton";
    private TextView mtv_bbtShow;

    private DeviceBleEx mDevBle;
    private BluetoothDevice mCutDevice = null ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_button);
        mtv_bbtShow = findViewById(R.id.tv_btt_show);


        String btAddr = UtilsApp.getBondedDevicesAddr();
        if(btAddr.equals("")){
            Log.e(TAG,"No device connection");
        }else{
            connectBt(btAddr);
        }
    }


    private void connectBt(String addr){
        mDevBle = new DeviceBleEx(BluetoothButton.this, addr);
        mDevBle.scanMeAndConnect(BleDevice.SCAN_ME_TYPE.SCAN_ME_BY_ADDR, 10000, true);
        mDevBle.registerCallback(mCb);
    }


    /**
     * 搜索连接界面
     */
    private void bleScan(){
        //BleScanActivity.selectBle(this,new byte[]{'K','F','Q'});
        BleScanActivity.selectBle(this);
    }

    private void setOnRecCmdCallback(){
        Applicat.gDevBle.setOnRecCmdCallback(mCmdCb1);
    }

    @Override
    public void onClick(View view) {
      if (view.getId() == R.id.btn_btt_connect) {
        bleScan();
      }
    }

    private DeviceBle.onCmdReceived mCmdCb1 = new DeviceBle.onCmdReceived(){

      @Override
      public void onCmdReceived(CmdEnums.CMD_ID cmdId) {
        Log.d(TAG,"onCmdReceived---CMD_ID:"+cmdId);
        switch (cmdId) {
          case BT_ON_KEY_HEADSET_P -> {
            Log.d(TAG, "--- BT_ON_KEY_HEADSET_P ---");
            updateKeyButtonStatus("BT_ON_KEY_HEADSET_P");
          }
          case BT_ON_KEY_HEADSET_2P -> {
            Log.d(TAG, "--- BT_ON_KEY_HEADSET_2P ---");
            updateKeyButtonStatus("BT_ON_KEY_HEADSET_2P");
          }
          case BT_ON_KEY_HEADSET_3P -> {
            Log.d(TAG, "--- BT_ON_KEY_HEADSET_3P ---");
            updateKeyButtonStatus("BT_ON_KEY_HEADSET_3P");
          }
          case BT_ON_KEY_HEADSET_HOLD -> {
            Log.d(TAG, "--- BT_ON_KEY_HEADSET_HOLD ---");
            updateKeyButtonStatus("BT_ON_KEY_HEADSET_HOLD");
          }
          case BT_ON_KEY_HEADSET_UP -> {
            Log.d(TAG, "--- BT_ON_KEY_HEADSET_UP ---");
            updateKeyButtonStatus("BT_ON_KEY_HEADSET_UP");
          }
        }
      }

      @Override
      public void onBTVersionReceived(String s) {

      }
    };

    private IBleDeviceCallback mCb = new BleDeviceCallbackImpNull(){
        @Override
        public void onScanTimeout(BleDevice bleDev) {
            super.onScanTimeout(bleDev);
            Log.e(TAG,"Connect device fail not found!" + bleDev.getAddr());
        }
        @Override
        public void onConnected(BleDevice bleDev, boolean connected, BluetoothGatt gatt, int status, int newState) {
            super.onConnected(bleDev, connected, gatt, status, newState);
            updateInfo();
        }
    };

    private void updateKeyButtonStatus(String mes){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mtv_bbtShow.setText(mes);
            }
        });
    }

    private void updateInfo(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                boolean connected = false;
                if (Applicat.gDevBle!=null){
                    connected = Applicat.gDevBle.isConnected();
                    if (connected) {
                        mtv_bbtShow.setText("Connection successful："+Applicat.gDevBle.getName());
                        setOnRecCmdCallback();
                    }else {
                        mtv_bbtShow.setText("Connection unsuccessful："+Applicat.gDevBle.getName());
                    }
                    showInfoLog(false,"Device:" + Applicat.gDevBle.getAddr() + " connect:" + connected);
                }else {
                    mtv_bbtShow.setText("No device connected");
                    showInfoLog(true,"No device connected");
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Applicat.gDevBle!=null){
            Applicat.gDevBle.setOnRecCmdCallback(null);
            Applicat.gDevBle.unregisterCallback(mCb);
            Applicat.gDevBle.disConnected();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG,"---onActivityResult requestCode:"+requestCode);
        if (requestCode == SEARCH_SELECT_BLE_DEV) {
            if (resultCode == Activity.RESULT_OK){
                if (Applicat.gDevBle !=null){
                    Applicat.gDevBle.disConnected();
                }
                Applicat.gDevBle = new DeviceBleEx(this,data.getStringExtra(EXTRA_BLE_DEV_ADD));
                Applicat.gDevBle.scanMeAndConnect(BleDevice.SCAN_ME_TYPE.SCAN_ME_BY_ADDR,10000,true);
                Applicat.gDevBle.registerCallback(mCb);
            }
        }
    }

    private void showInfoLog(boolean isE,String info){
        if(isE){
            Log.e(TAG,info);
        }else{
            Log.d(TAG,info);
        }
    }
}
