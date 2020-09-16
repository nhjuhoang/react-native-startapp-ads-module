// @flow

import React, { Children } from 'react';
import PropTypes from 'prop-types';
import { requireNativeComponent, View } from 'react-native';

const RNStartAppNativeBanner = requireNativeComponent('RNStartAppNativeBanner');

type Props = {
  onReceiveAd: () => null,
  onFailedToReceiveAd: Function,
}

const BannerNativeAd = ({ onFailedToReceiveAd, onReceiveAd, children }: Props) => {
  return (
    <RNStartAppNativeBanner
      onReceiveAd={onReceiveAd}
      onFailedToReceiveAd={onFailedToReceiveAd}
    >
      <View>
        {children}
      </View>
    </RNStartAppNativeBanner>
  );
}

BannerNativeAd.defaultProps = {
  onReceiveAd: () => null,
  onFailedToReceiveAd: () => null,
}

BannerNativeAd.propTypes = {
  onReceiveAd: PropTypes.func,
  onFailedToReceiveAd: PropTypes.func,
};

export default BannerNativeAd;
