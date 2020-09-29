package com.pikolive.module.ad.factory

import com.pikolive.module.ad.factory.platform.google.GoogleBannerAd
import com.pikolive.module.ad.factory.platform.google.GoogleInterstitialAd
import com.pikolive.module.ad.type.BannerAd
import com.pikolive.module.ad.type.InterstitialAd

/**
 * Creator: ED
 * Date: 2020/5/26 11:36 AM
 * Mail: salahayo3192@gmail.com
 *
 * Google 廣告生產工廠
 * **/
class GoogleAdFactory : AbstractAbFactory() {

    override fun providerInterstitialAd(params: InterstitialAd.Params): InterstitialAd {
        return GoogleInterstitialAd(params.context).apply {
            this.setAdUnitId(params.adUnitId)
            this.setListener(params.adListener)
        }
    }

    override fun providerBannerAd(params: BannerAd.Params): BannerAd {
        return GoogleBannerAd(params.context, params.adContainer).apply {
            this.setAdUnitId(params.adUnitId)
            this.setAdSize(params.adSize)
            this.setListener(params.adListener)
        }
    }

}