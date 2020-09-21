package com.nhjuhoang.rnstartappads;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import com.startapp.sdk.ads.banner.Banner;
import com.startapp.sdk.ads.banner.BannerListener;
import com.startapp.sdk.adsbase.model.AdPreferences;

public class RNStartAppBanner extends RelativeLayout implements BannerListener {

    public static final String EVENT_AD_RECEIVE = "onReceiveAd";
    public static final String EVENT_AD_FAILED_TO_RECEIVE = "onFailedToReceiveAd";
    public static final String EVENT_AD_CLICK = "onClick";

    ReactContext mContext;

    Banner bannerAd ;

    public RNStartAppBanner(ReactContext context) {
        super(context);
        mContext = context;
        AdPreferences prefs = new AdPreferences();
        prefs.setAdTag("banner");
        bannerAd = new Banner(context, prefs);
        bannerAd.setBannerListener(this);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getChildCount() == 0) addView(bannerAd);
        bannerAd.loadAd();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        bannerAd.hideBanner();
        if (getChildCount() > 0) removeAllViews();
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
        if (getWidth()> 0 && getHeight() > 0) {
            measure(getWidth(), getHeight());
            layout(getLeft(), getTop(), getRight(), getBottom());
        }
    }

    @Override
    public void onReceiveAd(View banner) {
        Log.d("ZOZOZOZOZOZOZOZOZO", "============= onReceiveAd =============");
        bannerAd.showBanner();
        sendEvent(EVENT_AD_RECEIVE ,null);
    }

    @Override
    public void onFailedToReceiveAd(View view) {
        Log.d("ZOZOZOZOZOZOZOZOZO", "============= onFailedToReceiveAd =============");
        WritableMap event = Arguments.createMap();
        sendEvent(EVENT_AD_FAILED_TO_RECEIVE ,event);
    }

    @Override
    public void onImpression(View view) {

    }

    @Override
    public void onClick(View view) {
        Log.d("ZOZOZOZOZOZOZOZOZO", "============= onClick =============");
        WritableMap event = Arguments.createMap();
        sendEvent(EVENT_AD_CLICK ,event);
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
