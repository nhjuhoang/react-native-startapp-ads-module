import { NativeModules, NativeEventEmitter } from 'react-native';

const { RNStartAppInterstitial } = NativeModules;

const eventEmitter = new NativeEventEmitter(RNStartAppInterstitial);

const addListener = (
  type: "onReceiveAd" | "onFailedToReceiveAd" | "adDisplayed" | "adNotDisplayed" | "adClicked" | "adHidden",
  handler: Function) => {
  switch (type) {
    case "onReceiveAd":
      eventEmitter.addListener('onReceiveAd', handler);
      break;
    case "onFailedToReceiveAd":
      eventEmitter.addListener('onFailedToReceiveAd', handler);
      break;
    case "adDisplayed":
      eventEmitter.addListener('adDisplayed', handler);
      break;
    case "adNotDisplayed":
      eventEmitter.addListener('adNotDisplayed', handler);
      break;
    case "adClicked":
      eventEmitter.addListener('adClicked', handler);
      break;
    case "adHidden":
      eventEmitter.addListener('adHidden', handler);
      break;
    default:
      break;
  }
}

const removeAllListeners = () => {
  eventEmitter.removeAllListeners('onReceiveAd');
  eventEmitter.removeAllListeners('onFailedToReceiveAd');
  eventEmitter.removeAllListeners('adDisplayed');
  eventEmitter.removeAllListeners('adNotDisplayed');
  eventEmitter.removeAllListeners('adClicked');
  eventEmitter.removeAllListeners('adHidden');
};

const load = () => {
  RNStartAppInterstitial.load()
}

const show = () => {
  RNStartAppInterstitial.show()
}

export default {
  load,
  show,
  addListener,
  removeAllListeners,
};