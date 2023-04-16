package com.awesomeproject;

import android.graphics.Bitmap;
import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

import java.io.ByteArrayOutputStream;

public class HellowCheckModule extends ReactContextBaseJavaModule {
    public HellowCheckModule(@Nullable ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @NonNull
    @Override
    public String getName() {
        return "HellowCheck";
    }
    @ReactMethod
    public  void sayHellow(String name, Callback callback){
        try{
            Bitmap bitmap = RandomBitMap.createRandomBitmap(400,400);
            ByteArrayOutputStream outputStream= new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,80,outputStream);
            byte[] arr= outputStream.toByteArray();

            WritableMap imageMap = Arguments.createMap();
            imageMap.putString("data", Base64.encodeToString(arr, Base64.DEFAULT));
            callback.invoke(null,arr);
        }
        catch (Exception e){
            callback.invoke(e,null);
        }
    }

}
