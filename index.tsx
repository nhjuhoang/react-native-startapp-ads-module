import { NativeModules } from 'react-native';
const { RNStartAppAds } = NativeModules;

export { default as BannerAd } from './BannerAd';
export { default as BannerNative } from './BannerNative';
export { default as Interstitial } from './Interstitial';

const initialize = (appId: string, useReturnAds: boolean) => {
  RNStartAppAds.initialize(appId, useReturnAds);
}

const setUserConsent = () => {
  RNStartAppAds.setUserConsent();
}

const setTestAdsEnabled = (value: boolean) => {
  RNStartAppAds.setTestAdsEnabled(value);
}



export default {
  initialize,
  setUserConsent,
  setTestAdsEnabled,
};