package com.nhjuhoang.rnstartappads;

import android.view.View;
import android.view.ViewGroup;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.startapp.android.publish.ads.banner.Banner;
import com.startapp.android.publish.ads.banner.BannerListener;

public class RNStartAppBanner extends Banner implements LifecycleEventListener, BannerListener {


    public static final String EVENT_AD_RECEIVE = "onReceiveAd";
    public static final String EVENT_AD_FAILED_TO_RECEIVE = "onFailedToReceiveAd";
    public static final String EVENT_AD_CLICK = "onClick";


    ReactContext mContext;
    public RNStartAppBanner(ReactContext context) {
        super(context);
        mContext = context;
        this.setBannerListener(this);
        context.addLifecycleEventListener(this);
    }

    @Override
    public void onViewAdded(View child) {
        //React-Native cannot autosize RNWebViews, so you have to manually specify style.height
        //or do some trickery
        //Turns out this is also true for all other WebViews, which are added internally inside this banner
        //So we just size them manually
        //Width and Height must be set in RN style
        super.onViewAdded(child);
        int width = getWidth();
        int height = getHeight();
        child.measure(width, height);
        child.layout(0, 0, width, height);

        View webView = ((ViewGroup) child).getChildAt(0);
        if (webView != null) {
            webView.measure(width, height);
            webView.layout(0, 0, width, height);
        }
    }


    @Override
    public void onReceiveAd(View banner) {
        int width = banner.getWidth();
        int height = banner.getHeight();
        int left = banner.getLeft();
        int top = banner.getTop();
        banner.measure(width, height);
        banner.layout(left, top, left + width, top + height);
        this.showBanner();
        sendEvent(EVENT_AD_RECEIVE ,null);
    }

    @Override
    public void onFailedToReceiveAd(View view) {
        WritableMap event = Arguments.createMap();
        sendEvent(EVENT_AD_FAILED_TO_RECEIVE ,event);
    }

    @Override
    public void onClick(View view) {
        WritableMap event = Arguments.createMap();
        sendEvent(EVENT_AD_CLICK ,event);
    }

    @Override
    public void onHostResume() {
    }

    @Override
    public void onHostPause() {

    }

    @Override
    public void onHostDestroy() {
        this.hideBanner();
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
