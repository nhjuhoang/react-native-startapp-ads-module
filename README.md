# react-native-startapp-ads-module
***Android supported***

## Usage
```javascript
  yarn add react-native-startapp-ads-module
```

## initialize first

```javascript
  import StartappAds from "react-native-startapp-ads-module";
  StartappAds.initialize('app id', 'return ads);
  StartappAds.initialize('123456', true);
```


## Interstitial
```javascript
  import Interstitial from 'react-native-startapp-ads-module/Interstitial';
  
  - load 
  Interstitial.load();
  - addListener
  type = 'onReceiveAd' | 'onFailedToReceiveAd' | 'adDisplayed' | 'adNotDisplayed'| 'adHidden' | 'adClicked';
  Interstitial.addListener('onReceiveAd', () => {
    // show ad 
    Interstitial.show()
  })
```

## Banner
```javascript
   import BannerAd from 'react-native-startapp-ads-module/BannerAd';
   <BannerAd style={{height: 50}} />
```

## BannerNative
```javascript
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
```
## TODOS
- [ ] supported IOS
