package com.pikolive.module.ad.factory.platform.facebook

import android.util.Log
import com.facebook.ads.Ad
import com.facebook.ads.AdError
import com.pikolive.module.ad.listener.AdListener
import com.pikolive.module.ad.type.AdObject
import com.pikolive.module.ad.type.BannerAd
import com.facebook.ads.InterstitialAdListener as FacebookAdListener

/**
 * Creator: ED
 * Date: 2020/5/26 2:43 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class FacebookAdListenerAdapter(
    private val adListener: AdListener?,
    private val adObject: AdObject
) : FacebookAdListener {
    private val TAG = "FacebookAdListener"
    override fun onInterstitialDisplayed(p0: Ad?) {
        Log.d(TAG, "onInterstitialDisplayed")

    }


    override fun onAdClicked(p0: Ad?) {
        Log.d(TAG, "onAdClicked")
        adListener?.onAdClick(adObject)
    }

    override fun onInterstitialDismissed(p0: Ad?) {
        Log.d(TAG, "onInterstitialDismissed")
    }

    override fun onError(p0: Ad?, p1: AdError?) {
        Log.d(TAG, "onError  ${p1?.errorCode} ,${p1?.errorMessage}")

        adListener?.onAdFailedToLoad(adObject, "${p1?.errorMessage}")
    }

    override fun onAdLoaded(p0: Ad?) {
        Log.d(TAG, "onAdLoaded")
        if (adObject is BannerAd) {
            adObject.setLoaded(true)
        }
        adListener?.onAdLoaded(adObject)
    }

    override fun onLoggingImpression(p0: Ad?) {
        Log.d(TAG, "onLoggingImpression")
    }
}
