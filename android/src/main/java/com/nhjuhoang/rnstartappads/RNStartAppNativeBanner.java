package com.nhjuhoang.rnstartappads;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.view.ReactViewGroup;
import com.startapp.sdk.ads.nativead.NativeAdDetails;
import com.startapp.sdk.ads.nativead.NativeAdPreferences;
import com.startapp.sdk.ads.nativead.StartAppNativeAd;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class RNStartAppNativeBanner extends ReactViewGroup implements AdEventListener {

    public static final String EVENT_AD_RECEIVE = "onReceiveAd";
    public static final String EVENT_AD_FAILED_TO_RECEIVE = "onFailedToReceiveAd";

    private StartAppNativeAd startAppNativeAd;
    private  AdEventListener adListener;

    public RNStartAppNativeBanner(ReactContext context) {
        super(context);
        startAppNativeAd = new StartAppNativeAd(context);
        adListener = this;
        loadAd();
    }


    private void loadAd() {
        NativeAdPreferences nativePrefs = new NativeAdPreferences()
                .setAdsNumber(1)
                .setAutoBitmapDownload(true)
                .setPrimaryImageSize(2);
        startAppNativeAd.loadAd(nativePrefs, adListener);
        Log.d("ZOZOZOZOZOZOZOZOZO", "======= LOAD AD NATIVE ==========");
    }

    @SuppressLint("WrongThread")
    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos =new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    private void sendEvent(String type, WritableMap payload) {
        WritableMap event = Arguments.createMap();
        event.putString("type", type);

        if (payload != null) {
            event.merge(payload);
        }
        ReactContext reactContext = (ReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class)
                .receiveEvent(this.getId(), type, event);
    }

    @Override
    public void onReceiveAd(Ad ad) {
        Log.d("ZOZOZOZOZOZOZOZOZO", "======= EVENT_AD_RECEIVE ==========");

        WritableMap params = Arguments.createMap();
        NativeAdDetails nativeAdDetails = null;
        ArrayList<NativeAdDetails> nativeAdsList = startAppNativeAd.getNativeAds();
        if (nativeAdsList.size() > 0) {
            nativeAdDetails = nativeAdsList.get(0);
        }

        // Verify that an ad was retrieved
        if (nativeAdDetails == null) {
            return;
        }

        params.putString("Title", nativeAdDetails.getTitle());
        params.putString("Description", nativeAdDetails.getDescription());
        params.putDouble("Rating", nativeAdDetails.getRating());
        params.putString("ImageUrl", nativeAdDetails.getImageUrl());
        params.putString("SecondaryImageUrl", nativeAdDetails.getSecondaryImageUrl());
        params.putString("Installs", nativeAdDetails.getInstalls());
        params.putString("Category", nativeAdDetails.getCategory());
        params.putString("PackacgeName", nativeAdDetails.getPackacgeName());
        params.putString("CampaignAction", nativeAdDetails.getCampaignAction().toString());
        params.putString("ImageBitmap", BitMapToString(nativeAdDetails.getImageBitmap()));
        params.putString("SecondaryImageBitmap", BitMapToString(nativeAdDetails.getSecondaryImageBitmap()));

        nativeAdDetails.registerViewForInteraction(this.getChildAt(0));

        sendEvent(EVENT_AD_RECEIVE, params);
    }

    @Override
    public void onFailedToReceiveAd(Ad ad) {
        Log.d("ZOZOZOZOZOZOZOZOZO", "======= EVENT_AD_FAILED_TO_RECEIVE ==========");
        WritableMap params = Arguments.createMap();
        params.putString("message", "EVENT_AD_FAILED_TO_RECEIVE");
        sendEvent(EVENT_AD_FAILED_TO_RECEIVE, params);
    }
}
