package com.pikolive.module.ad.factory

import com.pikolive.module.ad.enums.AdType
import com.pikolive.module.ad.type.AdObject
import com.pikolive.module.ad.type.BannerAd
import com.pikolive.module.ad.type.InterstitialAd

/**
 * Creator: ED
 * Date: 2020/5/26 11:33 AM
 * Mail: salahayo3192@gmail.com
 *
 * 抽象工廠：依據廣告類型的不同生產各類廣告，例：插頁、橫幅
 * **/
abstract class AbstractAbFactory {

    /**
     * 生產插頁廣告
     *
     * @param params 設定橫幅廣告的屬性
     *
     * @return 插頁廣告
     * */
    internal abstract fun providerInterstitialAd(params: InterstitialAd.Params): InterstitialAd


    /**
     * 生產橫幅廣告
     *
     * @param params 設定橫幅廣告的屬性
     *
     * @return 橫幅廣告
     * */
    internal abstract fun providerBannerAd(params: BannerAd.Params): BannerAd


    fun create(
        adType: AdType,
        params: AdObject.BaseParams
    ): AdObject {
        return when (adType) {
            AdType.Banner -> providerBannerAd(params as BannerAd.Params)
            AdType.Interstitial -> providerInterstitialAd(params as InterstitialAd.Params)
        }
    }

}