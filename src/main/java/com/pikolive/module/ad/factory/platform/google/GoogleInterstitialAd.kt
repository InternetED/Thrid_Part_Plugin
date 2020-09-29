package com.pikolive.module.ad.factory.platform.google

import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.pikolive.module.ad.listener.AdListener
import com.pikolive.module.ad.type.InterstitialAd
import com.google.android.gms.ads.InterstitialAd as GoogleInterstitialAd

/**
 * Creator: ED
 * Date: 2020/5/26 1:55 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class GoogleInterstitialAd(
    context: Context
) : InterstitialAd(context) {
    private val mInterstitialAd: GoogleInterstitialAd = GoogleInterstitialAd(mContext)
    private val mAdRequest: AdRequest = AdRequest.Builder().build()


    override fun setAdUnitId(adUnitId: String) {
        mInterstitialAd.adUnitId = adUnitId
    }

    override fun setListener(listener: AdListener?) {
        mInterstitialAd.adListener = GoogleAdListenerAdapter(listener, this)
    }

    override fun isLoadedAd(): Boolean {
        return mInterstitialAd.isLoaded
    }

    override fun loadAd() {
        mInterstitialAd.loadAd(mAdRequest)
    }

    override fun showAd() {
        if (isLoadedAd()) {
            mInterstitialAd.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mInterstitialAd.adListener = null
    }
}
