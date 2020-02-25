package com.nhjuhoang.rnstartappads;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppAd.AdMode;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;

import javax.annotation.Nullable;


public class RNStartAppInterstitialModule extends ReactContextBaseJavaModule {
    private static final String TAG = "RNStartAppInterstitial";

    private StartAppAd interstitial;

    public RNStartAppInterstitialModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {  return TAG;  }

    private void sendEvent(ReactContext reactContext,
                           String eventName,
                           @Nullable WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    @ReactMethod
    public void load() {
        this.loadAd(this.getInterstitial(), AdMode.AUTOMATIC);
    }

    @ReactMethod
    public void show(final Promise promise) {
        try {
            this.showAd(this.getInterstitial(), promise);
        } catch(Exception e) {
            promise.reject(e);
        }
    }


    private StartAppAd getInterstitial() {
        if (this.interstitial == null) this.interstitial = new StartAppAd(this.getReactApplicationContext());
        return this.interstitial;
    }

    private void loadAd(final StartAppAd startappAd, final AdMode mode) {
        startappAd.loadAd(mode, new AdEventListener() {
            @Override
            public void onReceiveAd(Ad ad) {
                try {
                    WritableMap params = Arguments.createMap();
                    sendEvent(
                            getReactApplicationContext(),
                            "onReceiveAd",
                            params
                    );
                    Log.d(TAG, "Received ad");
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
            }
            @Override
            public void onFailedToReceiveAd(Ad ad) {
                try {
                    WritableMap params = Arguments.createMap();
                    sendEvent(
                            getReactApplicationContext(),
                            "onFailedToReceiveAd",
                            params
                    );
                    Log.d(TAG, "Failed to receive ad");
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
            }
        });
    }

    private void showAd(final StartAppAd startappAd, final Promise promise) {
        startappAd.showAd(new AdDisplayListener() {
            @Override
            public void adDisplayed(Ad ad) {
                promise.resolve("display_interstitial");
                Log.d(TAG, "Ad displayed");
            }
            @Override
            public void adNotDisplayed(Ad ad) {
                promise.reject(new Exception("Ad not displayed"));
                Log.e(TAG, "Ad not displayed");
            }
            @Override
            public void adClicked(Ad ad) {
            }
            @Override
            public void adHidden(Ad ad) {
            }
        });
    }

}
