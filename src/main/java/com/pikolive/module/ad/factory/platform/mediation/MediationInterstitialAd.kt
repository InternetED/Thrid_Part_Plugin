package com.pikolive.module.ad.factory.platform.mediation

import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.ResponseInfo
import com.pikolive.module.ad.listener.AdListener
import com.pikolive.module.ad.type.InterstitialAd
import com.google.android.gms.ads.InterstitialAd as GoogleInterstitialAd

/**
 * Creator: ED
 * Date: 2020/5/26 1:55 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
open class MediationInterstitialAd(
    context: Context
) : InterstitialAd(context) {
    private val mInterstitialAd: GoogleInterstitialAd = GoogleInterstitialAd(mContext)
    private val mAdRequest: AdRequest = AdRequest.Builder().build()


    override fun setAdUnitId(adUnitId: String) {
        mInterstitialAd.adUnitId = adUnitId
    }

    override fun setListener(listener: AdListener?) {
        mInterstitialAd.adListener = MediationAdListenerAdapter(listener, this)
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

    fun getResponseInfo(): ResponseInfo? {
        return mInterstitialAd.responseInfo
    }
}
