package com.pikolive.module.ad.factory.platform.google

import android.util.Log
import com.pikolive.module.ad.listener.AdListener
import com.pikolive.module.ad.type.AdObject
import com.pikolive.module.ad.type.BannerAd
import com.google.android.gms.ads.AdListener as GoogleAdListener

/**
 * Creator: ED
 * Date: 2020/5/26 2:19 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class GoogleAdListenerAdapter(
    private val adListener: AdListener?,
    private val adObject: AdObject
) : GoogleAdListener() {
    private val TAG = "GoogleAdListener"

    override fun onAdImpression() {
        Log.d(TAG, "onAdImpression")
    }

    override fun onAdLeftApplication() {
        Log.d(TAG, "onAdLeftApplication")
    }

    override fun onAdClicked() {
        Log.d(TAG, "onAdClicked")
        adListener?.onAdClick(adObject)
    }

    override fun onAdFailedToLoad(p0: Int) {
        Log.d(TAG, "onAdFailedToLoad")
        adListener?.onAdFailedToLoad(adObject, "$p0")
    }

    override fun onAdClosed() {
        Log.d(TAG, "onAdClosed")
    }

    override fun onAdOpened() {
        Log.d(TAG, "onAdOpened")
    }

    override fun onAdLoaded() {
        Log.d(TAG, "onAdLoaded")
        if (adObject is BannerAd) {
            adObject.setLoaded(true)
        }
        adListener?.onAdLoaded(adObject)
    }
}