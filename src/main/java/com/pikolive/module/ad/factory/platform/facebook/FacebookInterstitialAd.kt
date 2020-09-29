package com.pikolive.module.ad.factory.platform.facebook

import android.content.Context
import com.pikolive.module.ad.listener.AdListener
import com.pikolive.module.ad.type.InterstitialAd
import com.facebook.ads.InterstitialAd as FacebookAd

/**
 * Creator: ED
 * Date: 2020/5/26 3:13 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class FacebookInterstitialAd(context: Context) : InterstitialAd(context) {

    private lateinit var interstitialAd: FacebookAd

    private var config: FacebookAd.InterstitialLoadAdConfig? = null


    override fun setAdUnitId(adUnitId: String) {
        interstitialAd = FacebookAd(mContext, adUnitId)
    }

    override fun setListener(listener: AdListener?) {
        config = interstitialAd.buildLoadAdConfig()
            .withAdListener(FacebookAdListenerAdapter(listener, this))
            .build()
    }

    override fun isLoadedAd(): Boolean {
        return interstitialAd.isAdLoaded
    }

    override fun loadAd() {
        interstitialAd.loadAd(config)
    }

    override fun showAd() {
        if (isLoadedAd()) {
            interstitialAd.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        config = null
        interstitialAd.destroy()
    }
}
