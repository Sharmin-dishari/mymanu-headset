package com.mymanu.companionapp;

import static com.kfree.btspp.BtSppBase.BT_SPP_STATE.STATE_CONNECTED;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kfree.BuildConfig;
import com.kfree.Log;
import com.kfree.app.ProgressDialogsEx;
import com.kfree.app.UtilsApp;
import com.kfree.bt.BtConst;
import com.kfree.btspp.BtSppBase;
import com.kfree.btspp.BtSppClient;
import com.kfree.btspp.SppScanActivity;
import com.kfree.esim.EsimUtils;
import com.kfree.esim.ProfileInfo;
import com.kfree.zxing.ScanActivity;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class EsimManagerActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "EsimManagerActivity";
    private static final int REQUEST_BLUETOOTH_PERMISSIONS = 1;
    private ActivityResultLauncher<Intent> enableBtLauncher;

    private LinearLayout mlinear_show,mlinear_btn;
    private FloatingActionButton addCard;
    private List<ProfileInfo> mProfileInfoList ;
    private ListView mListView;
    private ProfileListAdapter mListAdapter;
    private boolean FAKE_DATA_DEBUG = false;
    private ProgressDialog mConnectProgress = null;  // When entering the interface, waiting to detect whether the modem is OK progress prompt
    private ProgressDialogsEx mPdm = null;
    private ProgressDialog mAddProfileDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esim_manager);
        EsimUtils.copyFilesFromAssets(this);

        if (!checkBluetoothPermissions()) {
          requestBluetoothPermissions();
        }


        // Initialize ActivityResultLauncher for enabling Bluetooth
        enableBtLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
          if (result.getResultCode() == RESULT_OK) {
            if (mSppClient.getSppState() == STATE_CONNECTED) {
              mSppClient.disconnect();
            } else {
              // 打开蓝牙后,打开蓝牙选择界面
              SppScanActivity.selectBtSpp(this);
            }
          } else {
            Toast.makeText(this, "Bluetooth is required to continue", Toast.LENGTH_SHORT).show();
            checkBluetoothAndProceed();
          }
        });

        mlinear_show = findViewById(R.id.linear_show);
        mlinear_btn = findViewById(R.id.linear_btn);
        mListView = findViewById(R.id.list_name);
        View tbToolbar = findViewById(R.id.tbToolbar);
        RelativeLayout rlBack = tbToolbar.findViewById(R.id.rlBack);
        rlBack.setOnClickListener(this);
        rlBack.setVisibility(View.VISIBLE);
        addCard = findViewById(R.id.addCard);
        addCard.setOnClickListener(this);

        if(mSppClient.isConnected()){
            showList(true);
        }else{
            showList(false);
        }
        mPdm = new ProgressDialogsEx(this) {
            @Override
            public void onTimeOut(EventResult eventResult) {
                super.onTimeOut(eventResult);
                Log.e(TAG, "Process Timer Out!");
            }
        };

        initOkhttp();
    }

    @Override
    public void onNewIntent(Intent intent) {
      super.onNewIntent(intent);
      // Custom intent handling logic
    }

    @Override
    protected void onResume() {
      super.onResume();
      // Restore the custom plugin activity state
    }

    @Override
    protected void onPause() {
      super.onPause();
      // Save the custom plugin activity state
    }

  public boolean checkBluetoothPermissions() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
      // Android 13 and above
      return ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
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

  private void initConnect(){
        initProgressDialog();
        showProgressDialog("Connecting...");
        mhandler.sendEmptyMessageDelayed(0,5000);
    }

    private void showList(boolean show){
        if(show){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mlinear_btn.setVisibility(View.GONE);
                    mlinear_show.setVisibility(View.VISIBLE);
                }
            });
        }else{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mlinear_show.setVisibility(View.GONE);
                    mlinear_btn.setVisibility(View.VISIBLE);
                }
            });
        }
    }

  private void checkBluetoothAndProceed() {
    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if (bluetoothAdapter == null) {
      Toast.makeText(this, "Device doesn't support Bluetooth", Toast.LENGTH_SHORT).show();
      finish(); // Exit the activity if Bluetooth is not supported
    } else if (!bluetoothAdapter.isEnabled()) {
      Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
      enableBtLauncher.launch(enableBtIntent);
    } else {
      if (mSppClient.getSppState() == STATE_CONNECTED) {
        mSppClient.disconnect();
      } else {
        // 打开蓝牙后,打开蓝牙选择界面
        SppScanActivity.selectBtSpp(this);
      }
    }
  }

    @Override
    public void onClick(View view) {
      int id = view.getId();
      if (id == R.id.btn_connect) {
        checkBluetoothAndProceed();
      } else if (id == R.id.rlBack) {
        finish();
      } else if (id == R.id.addCard) {
        showBottomSheetDialog();
      }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"---onDestroy---");
        if(mSppClient.isConnected()){
            mSppClient.disconnect();
        }
        mSppClient = null ;
        mConnectProgress = null;
    }

    /**
     * get the Profile list
     */
    private void getProfile(){
        sendCMD(EsimUtils.getProfileCmd());
    }

    /**
     * get the Euicc info
     */
    private void getEuiccInfo(){
        sendCMD(EsimUtils.getEuiccInfoCmd());
    }

    /**
     * delete Profile by ICCID
     * @param iccid
     */
    private void deleteProfileByICCID(String iccid){
        sendCMD(EsimUtils.getDeleteProfileCmd(iccid));
    }

    /**
     * Add Profile by scanning the code
     * @param getcode
     */
    private void scanAddProfile(String getcode){
        sendCMD(EsimUtils.addProfile(getcode));
    }

    private BtSppClient mSppClient = new BtSppClient("SppClient",true) {
        @Override
        public void onStateChanged(BT_SPP_STATE state) {
            showInfo(state+"");
            showProgressDialog(state+" ...");
            if(state== BtSppBase.BT_SPP_STATE.STATE_CONNECTING||state==BtSppBase.BT_SPP_STATE.STATE_DISCOVERING){
                mhandler.removeMessages(0);
                mhandler.sendEmptyMessageDelayed(0,10000);
            }
        }
        @Override
        public void onConnectFail(BT_SPP_CONNECT_ERROR error, BluetoothDevice device) {
            showInfo("onConnectFail-"+error + " " + UtilsApp.getString(device));
            showList(false);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG,"---onConnectFail error:"+error+" device:"+device);
                    mPdm.finished(false,"");
                }
            });
        }
        @Override
        public void onDisconnect(BT_SPP_DISCONNECT_REASON reason,BluetoothDevice device) {
            showInfo("onDisconnect-" + reason + " " + UtilsApp.getString(device));
            showList(false);
        }
        @Override
        public void onConnected(BluetoothSocket socket, BluetoothDevice device) {
            showInfo("onConnected-" + UtilsApp.getString(device));
            showList(true);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mhandler.removeMessages(0);
                    Log.e(TAG,"---onConnected socket:"+socket+" device:"+device);
                    showProgressDialog("Get in the  profile list ...");
                    getProfile();
                }
            });
        }
        @Override
        public void onLineReceived(String line) {
            Log.e(TAG,"---onLineReceived line:"+line);
            switch (EsimUtils.getCmd(line)){
                case PROFILE_LIST://The obtained profile list
                    stopProgressDialog();
                    if(EsimUtils.getCmdStatus(line)){//successful
                        if(mProfileInfoList!=null) {
                            mProfileInfoList.clear();
                        }
                        mProfileInfoList = EsimUtils.getProfileList(line);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateEsimDatas(mProfileInfoList);
                            }
                        });
                    }else{//fail
                        showToast("Get ProfileInfoList failure");
                    }
                    break;
                case ENABLE_PROFILE:
                    Log.e(TAG,"---ENABLE_PROFILE");
                    mTimeOutHandler.removeMessages(0);
                    if(EsimUtils.getCmdStatus(line)) {//successful
                        Log.e(TAG,"---ENABLE_PROFILE SUCCESS");
                        showProgressDialog("Updating the list...");
                        mhandler.sendEmptyMessageDelayed(1,5000);//Delay 5 seconds to get the Profile list
                    }else{
                        Log.e(TAG,"---ENABLE_PROFILE FAIL");
                        stopProgressDialog();

                        showToast("Enable failure");
                    }
                    break;
                case EUICC_INFO:
                    if(EsimUtils.getCmdStatus(line)) {//successful
                        Log.e(TAG,"---EUICC_INFO getCmdStatus success");
                        showEuiccInfo(EsimUtils.getEuiccInfo(line));
                    }else{
                        showToast("Get EuiccInfo failure");
                    }
                    break;
                case DELETE_PROFILE:
                    Log.e(TAG,"---DELETE_PROFILE");
                    if(EsimUtils.getCmdStatus(line)) {//成功
                        showToast("Deleted successfully");
                        showProgressDialog("Updating the list...");
                        mhandler.sendEmptyMessageDelayed(1,5000);//Delay 5 seconds to get the Profile list
                    }else{
                        stopProgressDialog();
                        showToast("Deletion failure");
                    }
                    break;
                case ADD_PROFILE_PROGRESS:
                    try {
                        JSONObject jsonObject = new JSONObject(line);
                        String mes = jsonObject.getString("mes");
                        int percentage = jsonObject.getInt("percentage");
                        Message msg = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("mes", mes);
                        bundle.putInt("percentage", percentage);
                        msg.setData(bundle);
                        msg.what = 1;
                        mAddProfileHandler.sendMessage(msg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case ADD_PROFILE_STATUS:
                    Log.e(TAG,"----ADD_PROFILE_STATUS");
                    try {
                        JSONObject jsonObject = new JSONObject(line);
                        String mes = jsonObject.getString("mes");
                        boolean success = jsonObject.getBoolean("success");
                        if(mAddProfileDialog!=null) {
                            mAddProfileDialog.dismiss();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showProgressDialog("Updating the list...");
                            }
                        });
                        Log.e(TAG,"ADD_PROFILE_STATUS mes:"+mes+" success:"+success);
                        mhandler.sendEmptyMessageDelayed(1,5000);//Delay 5 seconds to get the Profile list
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case OKHTTP_REQUEST:
                    Log.e(TAG,"----OKHTTP_REQUEST");
                    try {
                        JSONObject jsonObject = new JSONObject(line);
                        String path = jsonObject.getString("path");
                        String body = jsonObject.getString("body");
                        String baseurlStr = jsonObject.getString("baseurl");
                        baseUrl = baseurlStr;
                        Log.e(TAG,"OKHTTP_REQUEST path:"+path+" body:"+body+" baseurl:"+baseUrl);
                        Message msg = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("path", path);
                        bundle.putString("body", body);
                        msg.setData(bundle);
                        msg.what = 0;
                        mAddProfileHandler.sendMessage(msg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                case DOWNLOAD_ERROR: {
                    Log.e(TAG,"---DOWNLOAD_ERROR");
                    if (mAddProfileDialog.isShowing()) {
                        mAddProfileDialog.dismiss();
                    }
                    String mes = EsimUtils.getDownloadErrorMes(line);
                    showToast(mes);
                }
                break;
                case DOWNLOAD_TIMEOUT:{
                    Log.e(TAG,"---DOWNLOAD_TIMEOUT");
                    if (mAddProfileDialog.isShowing()) {
                        mAddProfileDialog.dismiss();
                    }
                    String mes = EsimUtils.getDownLoadTimeOutMes(line);
                    showToast(mes);
                }
                break;

            }
            showInfo("onLineReceived-" + line);
        }

        @Override
        public void onDataReceived(byte[] data) {
            showInfo("onDataReceived-" + UtilsApp.byteBufferToHexString(data,data.length,30));
        }

        @Override
        public void onDataWrite(byte[] data) {
            showInfo("onDataWrite-" + UtilsApp.byteBufferToHexString(data,data.length,30));
        }
    };


    /**
     * show Euicc Info
     * @param info
     */
    private void showEuiccInfo(String info){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    AlertDialog.Builder builder=new AlertDialog.Builder(EsimManagerActivity.this);
                    builder.setMessage(info);
                    builder.setPositiveButton("confirm", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int arg1) {
                            // TODO Auto-generated method stub
                            dialog.dismiss();
                        }

                    });
                    builder.create().show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }



    /**
     * Show Toast
     * @param mes
     */
    private void showToast(String mes){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(EsimManagerActivity.this,mes,Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Show info
     * @param mes
     */
    private void showInfo(String mes){
        Log.e(TAG,"showInfo mes:"+mes);
    }

    /**
     * Initialize the progress box
     */
    private void initProgressDialog(){
        mConnectProgress = new ProgressDialog(this);
        mConnectProgress.setIndeterminate(true);
        mConnectProgress.setCancelable(false);
    }

    /**
     * Show progress box
     * @param mes
     */
    private void showProgressDialog(String mes){
        if(mConnectProgress !=null){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mConnectProgress.setMessage(mes);
                    mConnectProgress.show();
                }
            });
        }
    }

    /**
     * stop progress box
     */
    private void stopProgressDialog(){
        if(mConnectProgress !=null){
            if(mConnectProgress.isShowing()){
                mConnectProgress.cancel();
            }
        }
    }

    private void showAddProfileDialog(){
        mAddProfileDialog = new ProgressDialog(this);
        mAddProfileDialog.setCancelable(false);
        mAddProfileDialog.setTitle("Profile Download");
        mAddProfileDialog.setMessage("Download in progress");
        mAddProfileDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mAddProfileDialog.setMax(100);
        mAddProfileDialog.show();
        mAddProfileDialog.setCanceledOnTouchOutside(false);
    }

    private Handler mAddProfileHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String path = msg.getData().getString("path");
                    String body = msg.getData().getString("body");
                    Log.d(TAG,"mAddProfileHandler msg.what 0 path:"+path+" body:"+body);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e(TAG,"-----run");
                            Log.d(TAG,"request path:"+path+" body:"+body);
                            String responseStr = sendRequest(path,body);
                            int code = mResponseCode;
                            Log.d(TAG,"code:"+code+" responseStr:"+responseStr);
                            sendCMD(EsimUtils.getOKHttpResultData(code,responseStr));
                        }
                    }).start();
                    break;
                case 1:{
                    String mes = msg.getData().getString("mes");
                    int percentage = msg.getData().getInt("percentage");
                    mAddProfileDialog.setProgress(percentage);
                    mAddProfileDialog.setMessage(mes + percentage + "%");
                }
                break;
                case 2:{
                    getProfile();
                }
                break;

            }
        }
    };



    /**
     * Send instruction
     * @param cmd
     */
    private void sendCMD(String cmd){
        if(mSppClient.isConnected()) {
            mSppClient.write(cmd, true);
        }else{
            Toast.makeText(this,"Please connect first",Toast.LENGTH_SHORT).show();
        }
    }


    private Handler mhandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Log.d(TAG,"mhandler msg.what 0 stopProgressDialog");
                    stopProgressDialog();
                    break;
                case 1:
                    Log.d(TAG,"mhandler msg.what 1 getProfile");
                    getProfile();
                    break;
            }
        }
    };

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG,"---onActivityResult requestCode:"+requestCode+" resultCode:"+resultCode);
        if (requestCode == BtConst.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK)
                if(data!=null) {
                    initConnect();
                    mSppClient.connect(data);
                }
        } else if (requestCode == BtConst.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                // After turning on Bluetooth, open the Bluetooth selection screen
                SppScanActivity.selectBtSpp(this);
            } else {
                Toast.makeText(getApplicationContext()
                        , "Bluetooth was not enabled."
                        , Toast.LENGTH_SHORT).show();
                finish();
            }
        }else if(requestCode == 1){
            switch (resultCode) {
                case RESULT_OK:
                    Log.e(TAG,"---get sacnCode---RESULT_OK");
                    Bundle bundle = data.getExtras();
                    String getcode =  bundle.getString("code_result");
                    Log.e(TAG,"---get sacnCode---getcode："+getcode);
                    scanAddProfile(getcode);
                    showAddProfileDialog();
                    break;
            }
        }
    }

    private void showBottomSheetDialog() {
        UtilsApp.showListSelectDialog(this, "Select model:", new String[]{"ADD", "ScanCode", "Cancel"}, new UtilsApp.ISelectDialogListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, String itemStr) {
                showInfo("showBottomSheetDialog " +itemStr+" which:"+which);
                if(itemStr.equals("ADD")){
                    showInfo("showBottomSheetDialog ---ADD");
                    scanAddProfile("LPA:1$consumer.rsp.global$TN202307311047403200B4C4");//12131
                    showAddProfileDialog();
                }else if(itemStr.equals("ScanCode")){
                    showInfo("showBottomSheetDialog ---ScanCode");

                    Intent frontCamera = new Intent(EsimManagerActivity.this, ScanActivity.class);
                    frontCamera.putExtra("cameraType", Camera.CameraInfo.CAMERA_FACING_BACK);
                    startActivityForResult(frontCamera,1);
                }
            }
        });
    }

    private void updateEsimDatas(List<ProfileInfo> profiles){
        mListAdapter = null ;
        mListAdapter = new ProfileListAdapter(this,profiles);
        mListView.setAdapter(mListAdapter);
        //mListView.setItemsCanFocus(false);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG,"---setOnItemClickListener onItemClick position:"+position);
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG,"---setOnItemLongClickListener onItemLongClick position:"+position);
                showSelectDialog(profiles.get(position).providerName,profiles.get(position).iccidString,getMenu(profiles.get(position).isEnabled.equals("true")));
                return true;
            }
        });
    }

    private void showSelectDialog(String title,String iccid ,String[] StrMenu){
        UtilsApp.showListSelectDialog(this, title, StrMenu, new UtilsApp.ISelectDialogListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, String itemStr) {
                try {
                    Toast.makeText(EsimManagerActivity.this,"itemStr:"+itemStr,Toast.LENGTH_SHORT).show();
                    if(itemStr.equals(strDisable)){
                        //mIsEsimSwitch = false ;
                        if (FAKE_DATA_DEBUG) {
                            //mProfiles.get(selectIndex).setStatus(0);//Simulate update data
                        }else {
                            showEnableDialog(false,title,iccid);
                        }
                    }else if(itemStr.equals(strEnable)){
                        showEnableDialog(true,title,iccid);
                    }else if(itemStr.equals(strDelete)){
                        //deleteEsim();
                        deleteConfirmDialog(title,iccid);
                    }else if(itemStr.equals(strInfo)){
                        getEuiccInfo();
                    }
                } catch (Exception e) {
                    Log.e(TAG,"activite error",e);
                }
            }
        });
    }

    /**
     * Delete confirmation box
     * @param name
     * @param iccid
     */
    protected void deleteConfirmDialog(String name,String iccid){
        AlertDialog.Builder builder=new AlertDialog.Builder(EsimManagerActivity.this);
        builder.setMessage("Delete or not "+name);
        builder.setPositiveButton("confirm", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int arg1) {
                Toast.makeText(EsimManagerActivity.this, iccid, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                try {
                    showProgressDialog("deleting "+name);
                    deleteProfileByICCID(iccid);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }

        });
        builder.create().show();
    }

    private String[] mStrMenu = null;
    private String strDisable = "Disable";
    private String strEnable = "Enable";
    private String strDelete = "Delete";
    private String strInfo = "EuiccInfo";
    private String[] getMenu(boolean enbale){
        if(enbale){
            mStrMenu = new String[2];
            for (int i = 0; i < mStrMenu.length; i++) {
                if (i == 0) {
                    mStrMenu[0] = strDisable;
                } else if(i == 1){
                    mStrMenu[1] = strInfo;
                }
            }
        }else {
            mStrMenu = new String[3];
            for (int i = 0; i < mStrMenu.length; i++) {
                if (i == 0) {
                    mStrMenu[0] = strEnable;
                } else if(i == 1){
                    mStrMenu[1] = strDelete;
                } else if(i == 2){
                    mStrMenu[2] = strInfo;
                }
            }
        }
        return mStrMenu ;
    }

    public class ProfileListAdapter extends BaseAdapter {
        Context context;
        List<ProfileInfo> esimList;
        LayoutInflater mInflater;
        public ProfileListAdapter(Context context, List<ProfileInfo> mList){
            this.context = context;
            this.esimList = mList;
            mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return esimList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = null;
            if(convertView == null){
                convertView = mInflater.inflate(R.layout.esim_profile_item,parent,false);
                //convertView = mInflater.inflate(R.layout.esim_manager_item,parent,false);
                viewHolder = new ViewHolder();
                viewHolder.textView = convertView.findViewById(R.id.textView);
                viewHolder.enable = convertView.findViewById(R.id.switch1);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder)convertView.getTag();
            }
            viewHolder.textView.setText(esimList.get(position).providerName);//esimList.get(position).name  providerName
            if(esimList.get(position).isEnabled.equals("true")){
                viewHolder.enable.setChecked(true);
                int[][] states = new int[][] {
                        new int[] {-android.R.attr.state_checked},
                        new int[] {android.R.attr.state_checked},
                };
                int[] thumbColors = new int[] {
                        Color.BLACK,
                        Color.RED,
                };
                DrawableCompat.setTintList(DrawableCompat.wrap(viewHolder.enable.getThumbDrawable()), new ColorStateList(states, thumbColors));
            }else{
                viewHolder.enable.setChecked(false);
            }

            ViewHolder finalViewHolder = viewHolder;
            viewHolder.enable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG,"---setOnClickListener");
                    if(!esimList.get(position).isEnabled.equals("true")) {
                        showEnableDialog(finalViewHolder.enable,true,esimList.get(position).providerName,esimList.get(position).iccidString);
                    }else{
                        showEnableDialog(finalViewHolder.enable,false,esimList.get(position).providerName,esimList.get(position).iccidString);
                    }
                }
            });
            viewHolder.enable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Log.e(TAG,"---onCheckedChanged b:"+b);
                }
            });
            return convertView;
        }
    }
    public class ViewHolder{
        AppCompatTextView textView ;
        Switch enable;
    }

    private void showEnableDialog(boolean enable ,String providerName ,String iccid){
        String title = "";
        if(enable){
            title = "Do you want enable this profile?";
        }else{
            title = "Do you want disable this profile?";
        }
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            //enableEsim();//open new esim
                            Log.d(TAG,"---iccid:"+iccid);
                            if(enable) {
                                showProgressDialog("enabling：" + providerName);
                                sendCMD(EsimUtils.enableProfile(iccid,true));
                            }else{
                                showProgressDialog("disabling：" + providerName);
                                sendCMD(EsimUtils.enableProfile(iccid,false));
                            }
                            //sendBtSppCMD(EsimUtils.enableProfile(iccid,true));
                            mTimeOutHandler.sendEmptyMessageDelayed(0,15000);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showEnableDialog(Switch sw, boolean enable, String providerName, String iccid){
        String title = "";
        if(enable){
            title = "Do you want enable this profile?";
        }else{
            title = "Do you want disable this profile?";
        }
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            //enableEsim();//open new esim
                        Log.e(TAG,"----iccid:"+iccid);
                        if(enable) {
                            showProgressDialog("enabling：" + providerName);
                            sendCMD(EsimUtils.enableProfile(iccid,true));
                        }else{
                            showProgressDialog("disabling：" + providerName);
                            sendCMD(EsimUtils.enableProfile(iccid,false));
                        }
                        //sendBtSppCMD(EsimUtils.enableProfile(iccid,true));
                        mTimeOutHandler.sendEmptyMessageDelayed(0,15000);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(enable){
                            sw.setChecked(false);
                        }else{
                            sw.setChecked(true);
                        }
                    }
                })
                .show();
    }

    private Handler mTimeOutHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    //updateEsimDatas(mProfileInfoList);
                    stopProgressDialog();
                    showToast("Enable failure");
                    break;
            }
        }
    };


    //################################################# http #####################################################

    private void testResponseStr(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG,"-----run");
                String path = "/gsma/rsp2/es9plus/initiateAuthentication";
                String body = "{\"euiccChallenge\":\"TMF3KR5HCqv6BP6b9u9mmQ==\",\"euiccInfo1\":\"vyA1ggMCAgCpFgQUgTcPUSXQsdQI1MOyMubSXnlb6/uqFgQUgTcPUSXQsdQI1MOyMubSXnlb6/s=\",\"smdpAddress\":\"ggckoem.prod.ondemandconnectivity.com\"}";
                String responseStr = sendRequest(path,body);
                Log.e(TAG,"testResponseStr responseStr:"+responseStr);
            }
        }).start();
    }

    private OkHttpClient httpClient = new OkHttpClient();
    private static final String CERTIFICATE_FORMAT_X509 = "X.509";
    private static final String TLS_PROTOCOL = "TLS";

    private Map<String, String> headers;
    private String baseUrl = "ggckoem.prod.ondemandconnectivity.com";
    private Response response = null;

    private int mResponseCode = 0 ;

    private void initOkhttp(){
        Log.e(TAG,"----initOkhttp");
        this.baseUrl = checkAndSanitizeBaseUrl(baseUrl);
        this.headers = new HashMap<>();

        final RetryingOkHttpInterceptor interceptor = new RetryingOkHttpInterceptor();
        httpClient.interceptors().add(interceptor);

        //this.baseUrl = checkAndSanitizeBaseUrl(baseUrl);

        loadCertificatesFromAppData();
    }

    private String sendRequest(String path, String body){
        addHttpHeader(SmxxConstants.HEADER_PROTOCOL, SmxxConstants.HEADER_VALUE_PROTOCOL);
        addHttpHeader(SmxxConstants.HEADER_USERAGENT,SmxxConstants.HEADER_VALUE_USERAGENT);


        mResponseCode = 0 ;
        response = null ;
        URL completeUrl = null;
        try {
            completeUrl = new URL(getCompleteUrl(path));
        } catch (MalformedURLException e) {
            Log.e(TAG,"MalformedURLException error",e);
        }

        final RequestBody requestBody = RequestBody.create(MediaType.parse(CONTENT_TYPE_JSON), body);
        final Request.Builder requestBuilder = new Request.Builder().post(requestBody).url(completeUrl.toString()).headers(getDefaultHeaders());
        Log.d(TAG,"---sendRequest: completeUrl: " + completeUrl+" requestBody:"+requestBody);
        final Request request = requestBuilder.build();
        try {
            response = httpClient.newCall(request).execute();
        } catch (IOException e) {
            Log.e(TAG,"---error:",e);
        }
        Log.e(TAG,"---OkHttpServiceTest: response code = " + response.code());

        String responseStr = "";
        mResponseCode = response.code();
        switch (response.code()) {
            case CODE_OK:
                Log.e(TAG,"---CODE_OK");
                try {
                    responseStr = readResponseBody(response.body());
                } catch (IOException e) {
                    Log.e(TAG,"---responseStr error",e);
                }
                break;
            case CODE_NO_CONTENT:
                Log.e(TAG,"---CODE_NO_CONTENT");
                responseStr = "";
                break;
            default:
                // TODO check if special handling needed for those cases
                Log.e(TAG,"call::Unexpected status code" + response.code());
        }
        Log.e(TAG,"---OkHttpServiceTest response body:"+responseStr);
        return responseStr;
    }

    private String readResponseBody(final ResponseBody responseBody) throws IOException {
        final long contentLength = responseBody.contentLength();
        final boolean notifyProgress = contentLength != -1;

        InputStream inputStream = null;
        ByteArrayOutputStream bos = null;
        try {
            int read;
            int totalRead = 0;
            inputStream = responseBody.byteStream();
            bos = new ByteArrayOutputStream();

            final byte[] buffer = new byte[4 * 1024];
            while ((read = inputStream.read(buffer)) != -1) {
                totalRead += read;
                bos.write(buffer, 0, read);
            }
            return bos.toString("UTF-8");
        } finally {
            responseBody.close();
            if (inputStream != null) {
                inputStream.close();
            }
            if (bos != null) {
                bos.close();
            }
        }
    }

    private String checkAndSanitizeBaseUrl(final String baseUrl) {
        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new IllegalArgumentException("baseUrl cannot be null");
        }
        // TODO for instrumentation testing, use HTTP
        final String protocol = BuildConfig.DEBUG && baseUrl.startsWith("localhost:") ? "http://" : "https://";
        // remove trailing slash
        if (baseUrl.endsWith("/")) {
            return protocol + baseUrl.substring(0, baseUrl.length() - 1);
        } else {
            return protocol + baseUrl;
        }
    }

    protected void loadCertificatesFromAppData() {
        Log.e(TAG,"loadCertificatesFromAppData");
        try {
            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".pem");
                }
            };

            File[] files = this.getExternalFilesDir("").listFiles(filter);
            if (files != null && files.length > 0) {

                CertificateFactory certificateFactory = CertificateFactory.getInstance(CERTIFICATE_FORMAT_X509);
                KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load(null, null);

                for (File file : files) {
                    Log.e(TAG,"Adding Cert :"+file.getName());
                    InputStream fileInputStream = new FileInputStream(file);
                    keyStore.setCertificateEntry(file.getName().trim(), certificateFactory.generateCertificate(fileInputStream));
                }

                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(keyStore);

                SSLContext sslContext = SSLContext.getInstance(TLS_PROTOCOL);
                sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

                this.httpClient.setSslSocketFactory(sslContext.getSocketFactory());
            } else {
                Log.e(TAG,"No TLS PEM-encoded files in app data location to add.");
            }

        } catch (CertificateException | KeyStoreException | IOException | NoSuchAlgorithmException | KeyManagementException e) {
            Log.e(TAG,"loadCertificatesFromAppData",e);
        }
    }

    private static class RetryingOkHttpInterceptor implements Interceptor {
        private static final int MAX_RETRY_COUNT = 3;
        private static final int BASE_RETRY_INTERVAL_SECONDS = 10;

        RetryingOkHttpInterceptor() {

        }

        @Override

        public Response intercept(final Interceptor.Chain chain) throws IOException {

            boolean successful = false;
            int retryNum = 0;
            int interval;
            Request newRequest = chain.request();
            Response response = null;
            while (!successful) {
                try {
                    response = chain.proceed(newRequest);
                    successful = response.isSuccessful();
                    Log.e(TAG,"response:"+response+" successful:"+successful);
                    //retry mechanism for invalid status code
                    while (!successful && retryNum < MAX_RETRY_COUNT) {

                        // pattern : RETRY_IN   TERVAL * 1, RETRY_INTERVAL * 2, RETRY_INTERVAL * 4, ...
                        interval = (int) (BASE_RETRY_INTERVAL_SECONDS * Math.pow(2, retryNum));
                        Log.e(TAG,"intercept : retry interval :" + interval + "s");
                        try {
                            Thread.sleep(interval * 1000);
                        } catch (final InterruptedException e) {
                            // wrap it in IOException
                            throw new IOException(e);
                        }
                        Log.e(TAG,"intercept : retry attempt #" + retryNum);
                        response = chain.proceed(chain.request());
                        Log.e(TAG,"intercept : response code " + response.code());
                        successful = response.isSuccessful();
                        retryNum++;
                    }

                    if (retryNum >= MAX_RETRY_COUNT) {
                        break;
                    }
                } catch (final Exception ex) {
                    //retry mechanism for unknown exception
                    Log.e(TAG,"retryNum:" + retryNum);
                    if (retryNum >= MAX_RETRY_COUNT) {
                        throw ex;
                    } else {
                        interval = (int) (BASE_RETRY_INTERVAL_SECONDS * Math.pow(2, retryNum));
                        Log.e(TAG,"intercept retry:" + retryNum + ", Exception: " + ex.getMessage());
                        try {
                            if(retryNum==3){
                                break;
                            }else{
                                Thread.sleep(interval * 1000);
                            }
                        } catch (final InterruptedException e) {
                            // wrap it in IOException
                            throw new IOException(e);
                        }
                    }
                    retryNum++;
                }
            }
            return response;
        }
    }

    private void addHttpHeader(final String header, final String value) {
        if (headers != null) {
            headers.put(header, value);
        }

    }

    private Headers getDefaultHeaders() {
        Log.e(TAG,"adding default header");
        final Headers.Builder builder = new Headers.Builder();
        builder.add(HEADER_CONNECTION, HEADER_VALUE_KEEP_ALIVE);
        builder.add(HEADER_PROXY_CONNECTION, HEADER_VALUE_KEEP_ALIVE);

        for (final Map.Entry<String, String> entry : headers.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }

        return builder.build();
    }

    public String getCompleteUrl(final String path) {
        Log.e(TAG,"getCompleteUrl: path="+path+"  base url:"+baseUrl);

        final String completeUrl;
        if (path.charAt(0) == '/') {
            completeUrl = baseUrl + path;
        } else {
            completeUrl = baseUrl + "/" + path;
        }
        Log.e(TAG,"getCompleteUrl: completeUrl ="+ completeUrl);
        return completeUrl;
    }

    public static final String HEADER_CONNECTION = "connection";
    public static final String HEADER_PROXY_CONNECTION = "proxy-connection";
    public static final String HEADER_VALUE_KEEP_ALIVE = "Keep-Alive";

    public static final String CONTENT_TYPE_JSON = "application/json";

    public static final int CODE_OK = 200;
    public static final int CODE_NO_CONTENT = 204;
    public static final int CODE_BAD_REQUEST = 400;
    public static final int CODE_NOT_FOUND = 404;
    public static final int CODE_SERVER_ERROR = 500;

}
