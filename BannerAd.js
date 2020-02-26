import React from 'react';
import PropTypes from 'prop-types';
import { requireNativeComponent } from 'react-native';

function BannerAd({ ...props }) {

  return (
    <RNStartAppBannerView
      {...props}
      onReceiveAd={() => console.log('---- 👉🏼 onReceiveAd')}
      onFailedToReceiveAd={() => console.log('---- 👉🏼 onFailedToReceiveAd')}
      onClick={() => console.log('---- 👉🏼 onClick')}
    />
  );
}

const RNStartAppBannerView = requireNativeComponent(
  'RNStartAppBannerView',
  BannerAd,
);

BannerAd.propTypes = {
  onReceiveAd: PropTypes.func,
  onFailedToReceiveAd: PropTypes.func,
  onClick: PropTypes.func,
};

export default BannerAd;
