package com.nhjuhoang.rnstartappads;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

import java.util.Map;

import javax.annotation.Nonnull;

public class RNStartAppNativeBannerViewManager extends ViewGroupManager<RNStartAppNativeBanner> {

    private static final String TAG = "RNStartAppNativeBanner";

    RNStartAppNativeBanner rnStartAppNativeBanner;

    @Nonnull
    @Override
    public String getName() {
        return TAG;
    }

    @Nonnull
    @Override
    protected RNStartAppNativeBanner createViewInstance(final ThemedReactContext reactContext) {
        rnStartAppNativeBanner = new RNStartAppNativeBanner(reactContext);
        return rnStartAppNativeBanner;
    }

    @Nonnull
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        MapBuilder.Builder<String, Object> builder = MapBuilder.builder();
        builder.put(RNStartAppBanner.EVENT_AD_RECEIVE, MapBuilder.of("registrationName", RNStartAppBanner.EVENT_AD_RECEIVE));
        builder.put(RNStartAppBanner.EVENT_AD_FAILED_TO_RECEIVE, MapBuilder.of("registrationName", RNStartAppBanner.EVENT_AD_FAILED_TO_RECEIVE));
        return builder.build();
    }

}
