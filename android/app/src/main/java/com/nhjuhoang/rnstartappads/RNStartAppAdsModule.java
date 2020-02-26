package com.nhjuhoang.rnstartappads;

import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

import com.facebook.react.bridge.ReactMethod;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;

public class RNStartAppAdsModule extends ReactContextBaseJavaModule {
    private static final String TAG = "RNStartAppAds";

    public RNStartAppAdsModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {  return TAG;  }

    @ReactMethod
    public void initialize(final String appId, final boolean useReturnAds, final Promise promise) {
        try {
            StartAppAd.disableSplash();
            StartAppSDK.init(this.getReactApplicationContext(), appId, useReturnAds);
            promise.resolve("Initialized success");
            Log.d(TAG, "Initialized success");
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void setUserConsent(final boolean value, final Promise promise) {
        try {
            StartAppSDK.setUserConsent(
                    this.getReactApplicationContext(),
                    "pas",
                    System.currentTimeMillis(),
                    value
            );
            promise.resolve(null);
            Log.d(TAG, "Set user consent");
        } catch (Exception e) {
            promise.reject(e);
        }
    }

}
