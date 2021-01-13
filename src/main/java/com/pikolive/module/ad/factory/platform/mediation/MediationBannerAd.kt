package com.pikolive.module.ad.factory.platform.mediation

import android.content.Context
import android.view.ViewGroup
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.pikolive.module.ad.listener.AdListener
import com.pikolive.module.ad.type.BannerAd
import com.google.android.gms.ads.AdSize as GoogleAdSize

/**
 * Creator: ED
 * Date: 2020/5/26 2:47 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class MediationBannerAd(
    context: Context, viewGroup: ViewGroup
) : BannerAd(context, viewGroup) {

    private val adView = AdView(mContext)
    private val adRequest: AdRequest = AdRequest.Builder().build()


    override fun setAdUnitId(adUnitId: String) {
        adView.adUnitId = adUnitId
    }

    override fun setListener(listener: AdListener?) {
        adView.adListener = MediationAdListenerAdapter(listener, this)
    }

    override fun setAdSize(adSize: AdSize) {
        val googleAdSize = when (adSize) {
            AdSize.SMALL -> GoogleAdSize.SMART_BANNER
            AdSize.MIDDLE -> GoogleAdSize.MEDIUM_RECTANGLE
        }
        adView.adSize = googleAdSize
    }

    override fun loadAd() {
        adView.loadAd(adRequest)
    }

    override fun showAd() {
        if (viewGroup.childCount == 0) {
            viewGroup.addView(adView, viewGroup.layoutParams)
        }
    }

    override fun onDestroy() {
        viewGroup.removeAllViews()
        adView.adListener = null
        adView.destroy()
    }
}

