package com.nhjuhoang.rnstartappads;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

import java.util.Map;

import javax.annotation.Nonnull;

public class RNStartAppBannerViewManager extends ViewGroupManager<RNStartAppBanner> {

    private static final String TAG = "RNStartAppBannerView";

    RNStartAppBanner rnStartAppBanner;

    @Nonnull
    @Override
    public String getName() {
        return TAG;
    }

    @Nonnull
    @Override
    protected RNStartAppBanner createViewInstance(final ThemedReactContext reactContext) {
        rnStartAppBanner = new RNStartAppBanner(reactContext);
        return rnStartAppBanner;
    }

    @Nonnull
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        MapBuilder.Builder<String, Object> builder = MapBuilder.builder();
        builder.put(RNStartAppBanner.EVENT_AD_RECEIVE, MapBuilder.of("registrationName", RNStartAppBanner.EVENT_AD_RECEIVE));
        builder.put(RNStartAppBanner.EVENT_AD_FAILED_TO_RECEIVE, MapBuilder.of("registrationName", RNStartAppBanner.EVENT_AD_FAILED_TO_RECEIVE));
        builder.put(RNStartAppBanner.EVENT_AD_CLICK, MapBuilder.of("registrationName", RNStartAppBanner.EVENT_AD_CLICK));
        return builder.build();
    }

}
