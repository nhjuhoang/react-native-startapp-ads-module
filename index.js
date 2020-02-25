// @flow

import { NativeModules, requireNativeComponent, NativeEventEmitter } from 'react-native';

const { RNStartAppAds } = NativeModules;


const initialize = (appId: string, useReturnAds: boolean) => {
  RNStartAppAds.initialize(appId, useReturnAds);
}

const setUserConsent = (value: boolean) => {
  RNStartAppAds.setUserConsent();
}

export default {
  initialize,
  setUserConsent
};