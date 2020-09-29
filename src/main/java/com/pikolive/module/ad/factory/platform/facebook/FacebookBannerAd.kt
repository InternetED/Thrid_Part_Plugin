package com.pikolive.module.ad.factory.platform.facebook

import android.content.Context
import android.view.ViewGroup
import com.facebook.ads.AdView
import com.pikolive.module.ad.listener.AdListener
import com.pikolive.module.ad.type.BannerAd
import com.facebook.ads.AdSize as FacebookAdSize


/**
 * Creator: ED
 * Date: 2020/5/26 3:47 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class FacebookBannerAd(context: Context, viewGroup: ViewGroup) : BannerAd(context, viewGroup) {

    private var adView: AdView? = null

    private var config: AdView.AdViewLoadConfig? = null

    private var mAdUnitId: String? = null
    private var mAdListener: AdListener? = null
    private var mAdSize: FacebookAdSize? = null


    override fun setAdUnitId(adUnitId: String) {
        this.mAdUnitId = adUnitId
    }

    override fun setListener(listener: AdListener?) {
        this.mAdListener = listener
    }

    override fun setAdSize(adSize: AdSize) {
        val facebookAdSize = when (adSize) {
            AdSize.SMALL -> FacebookAdSize.BANNER_HEIGHT_50
            AdSize.MIDDLE -> FacebookAdSize.RECTANGLE_HEIGHT_250
        }
        this.mAdSize = facebookAdSize
    }

    override fun loadAd() {
        if (adView == null) {
            adView = AdView(mContext, mAdUnitId, mAdSize).apply {
                config = this.buildLoadAdConfig()
                    .withAdListener(FacebookAdListenerAdapter(mAdListener, this@FacebookBannerAd))
                    .build()
            }
        }

        adView?.loadAd(config)

    }

    override fun showAd() {
        if (viewGroup.childCount == 0) {
            viewGroup.addView(adView, viewGroup.layoutParams)
        }
    }

    override fun onDestroy() {
        viewGroup.removeAllViews()
        config = null
        adView?.destroy()
    }

}