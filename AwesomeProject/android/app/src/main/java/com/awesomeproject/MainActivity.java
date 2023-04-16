package com.awesomeproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.awesomeproject.Camera.CameraFrames;
import com.awesomeproject.Camera.ManageExternalCamera;
import com.awesomeproject.Camera.R100PermissionListener;
import com.awesomeproject.Camera.R100PermissionManager;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint;
import com.facebook.react.defaults.DefaultReactActivityDelegate;

import java.io.ByteArrayOutputStream;

public class MainActivity extends ReactActivity {
  String  TAG = "CheckCamera";
  boolean hasPermission=false;
  R100PermissionManager permissionManager;
  boolean hasCameraPermission=false;
  ManageExternalCamera externalCamera;

  @Override
  protected void onStart() {
    super.onStart();
    permissionManager = new R100PermissionManager(this,listener);
    externalCamera = new ManageExternalCamera(this,this);
    externalCamera.setFrameListner(frameListner);
  }
  CameraFrames frameListner = new CameraFrames() {
    @Override
    public void onCameraFrame(Bitmap bitmap, long timestamp) {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
      byte[] bytes = outputStream.toByteArray();
      HellowCheckModule.sendCameraFrame(bytes);
    }

    @Override
    public void onCameraFrame(byte[] bytes, String format, int height, int width) {
      Log.i(TAG,"Byte arrary comming");
    }
  };
  R100PermissionListener listener = new R100PermissionListener() {
    @Override
    public void permissionGranted(Boolean bool, int code) {
      Log.i(TAG,"Permission Granted");
      hasCameraPermission = true;
      externalCamera.openCamera();
    }
  };
  @Override
  protected void onResume() {
    super.onResume();
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
      ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, 20);
    }
    else if(!hasPermission){
      permissionManager.requestCameraPermission(8457,34839);
    }
  }

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
    return "AwesomeProject";
  }

  /**
   * Returns the instance of the {@link ReactActivityDelegate}. Here we use a util class {@link
   * DefaultReactActivityDelegate} which allows you to easily enable Fabric and Concurrent React
   * (aka React 18) with two boolean flags.
   */
  @Override
  protected ReactActivityDelegate createReactActivityDelegate() {
    return new DefaultReactActivityDelegate(
        this,
        getMainComponentName(),
        // If you opted-in for the New Architecture, we enable the Fabric Renderer.
        DefaultNewArchitectureEntryPoint.getFabricEnabled(), // fabricEnabled
        // If you opted-in for the New Architecture, we enable Concurrent React (i.e. React 18).
        DefaultNewArchitectureEntryPoint.getConcurrentReactEnabled() // concurrentRootEnabled
        );
  }
}
