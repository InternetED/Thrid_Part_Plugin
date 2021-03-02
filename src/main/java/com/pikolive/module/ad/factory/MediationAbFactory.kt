package com.pikolive.module.ad.factory

import com.pikolive.module.ad.factory.platform.mediation.MediationBannerAd
import com.pikolive.module.ad.factory.platform.mediation.MediationInterstitialAd
import com.pikolive.module.ad.type.BannerAd
import com.pikolive.module.ad.type.InterstitialAd

class MediationAbFactory : AbstractAbFactory() {
    override fun providerInterstitialAd(params: InterstitialAd.Params): InterstitialAd {
        return MediationInterstitialAd(params.context).apply {
            this.setAdUnitId(params.adUnitId)
            this.setListener(params.adListener)
        }
    }

    override fun providerBannerAd(params: BannerAd.Params): BannerAd {
        return MediationBannerAd(params.context, params.adContainer).apply {
            this.setAdUnitId(params.adUnitId)
            this.setAdSize(params.adSize)
            this.setListener(params.adListener)
        }
    }
}