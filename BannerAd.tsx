import React from 'react';
import PropTypes from 'prop-types';
import { requireNativeComponent, ViewStyle } from 'react-native';

const RNStartAppBannerView = requireNativeComponent('RNStartAppBannerView');

type Props = {
  onReceiveAd: Function,
  onFailedToReceiveAd: Function,
  onClick: Function,
  style: ViewStyle
}

function BannerAd({ onFailedToReceiveAd, onReceiveAd, onClick, ...props }: Props) {
  return (
    <RNStartAppBannerView
      {...props}
      onReceiveAd={onReceiveAd}
      onFailedToReceiveAd={onFailedToReceiveAd}
      onClick={onClick}
    />
  );
}

BannerAd.defaultProps = {
  onReceiveAd: () => null,
  onFailedToReceiveAd: () => null,
  onClick: () => null,
}

BannerAd.propTypes = {
  onReceiveAd: PropTypes.func,
  onFailedToReceiveAd: PropTypes.func,
  onClick: PropTypes.func,
};

export default BannerAd;
