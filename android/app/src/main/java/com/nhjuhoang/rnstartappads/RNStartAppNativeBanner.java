package com.nhjuhoang.rnstartappads;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.view.ReactViewGroup;
import com.startapp.android.publish.ads.nativead.NativeAdDetails;
import com.startapp.android.publish.ads.nativead.NativeAdPreferences;
import com.startapp.android.publish.ads.nativead.StartAppNativeAd;
import com.startapp.android.publish.adsCommon.Ad;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class RNStartAppNativeBanner extends ReactViewGroup {

    public static final String EVENT_AD_RECEIVE = "onReceiveAd";
    public static final String EVENT_AD_FAILED_TO_RECEIVE = "onFailedToReceiveAd";

    private StartAppNativeAd startAppNativeAd;

    public RNStartAppNativeBanner(ReactContext context) {
        super(context);
        startAppNativeAd = new StartAppNativeAd(context);
        loadAd(this.getRootView());
    }

    private void loadAd(final View view) {
        NativeAdPreferences nativePrefs = new NativeAdPreferences()
                .setAdsNumber(1)
                .setAutoBitmapDownload(true)
                .setPrimaryImageSize(2);
        AdEventListener adListener = new AdEventListener() {     // Callback Listener
            @Override
            public void onReceiveAd(Ad arg0) {
                Log.d("ZOZOZOZOZOZOZOZOZO", "onReceiveAd");

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

                nativeAdDetails.registerViewForInteraction(view);

                sendEvent(EVENT_AD_RECEIVE, params);
            }

            @Override
            public void onFailedToReceiveAd(Ad arg0) {
                Log.d("onFailedToReceiveAd", "ERROR ERROR ERROR ERROR ERROR ERROR ERROR");
                WritableMap params = Arguments.createMap();
                params.putString("message", "Error while loading Ad");
                sendEvent(EVENT_AD_FAILED_TO_RECEIVE, params);
            }
        };

        startAppNativeAd.loadAd(nativePrefs, adListener);
    }

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

}
