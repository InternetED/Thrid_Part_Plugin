package com.pikolive.module.ad.factory

import com.pikolive.module.ad.factory.platform.facebook.FacebookBannerAd
import com.pikolive.module.ad.factory.platform.facebook.FacebookInterstitialAd
import com.pikolive.module.ad.type.BannerAd
import com.pikolive.module.ad.type.InterstitialAd

/**
 * Creator: ED
 * Date: 2020/5/26 11:36 AM
 * Mail: salahayo3192@gmail.com
 *
 * Facebook 廣告生產工廠
 * **/
class FacebookAdFactory : AbstractAbFactory() {


    override fun providerInterstitialAd(params: InterstitialAd.Params): InterstitialAd {
        return FacebookInterstitialAd(params.context).apply {
            this.setAdUnitId(params.adUnitId)
            this.setListener(params.adListener)
        }
    }

    override fun providerBannerAd(params: BannerAd.Params): BannerAd {
        return FacebookBannerAd(params.context, params.adContainer).apply {
            this.setAdUnitId(params.adUnitId)
            this.setAdSize(params.adSize)
            this.setListener(params.adListener)
        }
    }

}