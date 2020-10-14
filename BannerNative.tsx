import React from 'react';
import { requireNativeComponent, View } from 'react-native';

const RNStartAppNativeBanner = requireNativeComponent('RNStartAppNativeBanner');

type Props = {
  onReceiveAd: (data: AdDataType) => void,
  onFailedToReceiveAd: () => void,
  children?: React.ReactNode
}

type AdDataType = {
  Title: string
  Description: string
  Rating: number
  ImageUrl: string
  SecondaryImageUrl: string
  Installs: string
  Category: string
  getPackacgeName: string
  CampaignAction: string
  ImageBitmap: string
  SecondaryImageBitmap: string
}

function NativeBanner({ onFailedToReceiveAd, onReceiveAd, children }: Props) {
  return (
    <RNStartAppNativeBanner
      onReceiveAd={(data) => { onReceiveAd(data.nativeEvent); }}
      onFailedToReceiveAd={onFailedToReceiveAd}
    >
      <View>
        {children}
      </View>
    </RNStartAppNativeBanner>
  );
}

NativeBanner.defaultProps = {
  onReceiveAd: (data: AdDataType) => { },
  onFailedToReceiveAd: () => { },
}

export default NativeBanner