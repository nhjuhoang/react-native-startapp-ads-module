# react-native-startapp-ads

// initialize first
import StartappAds from "react-native-startapp-ads-module";
StartappAds.initialize('app ID', return ads: boolean);

// Interstitial
import Interstitial from 'react-native-startapp-ads-module/Interstitial';

// load 
Interstitial.load();

// add addListener
type = 'onReceiveAd' | 'onFailedToReceiveAd' | 'adDisplayed' | 'adNotDisplayed'| 'adHidden' | 'adClicked';
Interstitial.addListener('onReceiveAd', () => {
  // show ad 
  Interstitial.show()
)

// Banner
import BannerAd from 'react-native-startapp-ads-module/BannerAd';
<BannerAd style={{height: 50}} />

// BannerNative
import BannerNative from 'react-native-startapp-ads-module/BannerNative';
 <BannerNative
    onReceiveAd={dataAds => {
        console.log(dataAds)
     }}
     onFailedToReceiveAd={() =>
        console.log('---- 👉🏼 onFailedToReceiveAd')
     }
 >
      <View>
         {custom banner native}
      </View>
</BannerNative>



