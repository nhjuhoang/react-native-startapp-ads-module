// @flow

import { NativeModules, requireNativeComponent, NativeEventEmitter } from 'react-native';

const { RNStartAppAds } = NativeModules;

const initialize = async (appId: string, useReturnAds: boolean) => {
  await RNStartAppAds.initialize(appId, useReturnAds);
}

const setUserConsent = async (value: boolean) => {
  await RNStartAppAds.setUserConsent();
}

export default {
  initialize,
  setUserConsent
};

export const Interstitial = './Interstitial';