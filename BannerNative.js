// @flow

import React, { Children } from 'react';
import PropTypes from 'prop-types';
import { requireNativeComponent, View } from 'react-native';

const RNStartAppNativeBanner = requireNativeComponent('RNStartAppNativeBanner');

type Props = {
  onReceiveAd: (data: Object) => null,
  onFailedToReceiveAd: Function,
}

function BannerAd({ onFailedToReceiveAd, onReceiveAd, onClick, children }: Props) {
  return (
    <RNStartAppNativeBanner
      onReceiveAd={onReceiveAd}
      onFailedToReceiveAd={onFailedToReceiveAd}
    >
      {children}
    </RNStartAppNativeBanner>
  );
}

BannerAd.defaultProps = {
  onReceiveAd: (data: Object) => null,
  onFailedToReceiveAd: () => null,
}

BannerAd.propTypes = {
  onReceiveAd: PropTypes.func,
  onFailedToReceiveAd: PropTypes.func,
};

export default BannerAd;
