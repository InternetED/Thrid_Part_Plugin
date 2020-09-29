package com.pikolive.module.ad.type

import android.content.Context
import com.pikolive.module.ad.enums.AdType
import com.pikolive.module.ad.listener.AdListener

/**
 * Creator: ED
 * Date: 2020/5/26 11:42 AM
 * Mail: salahayo3192@gmail.com
 *
 * 插頁廣告
 * **/
abstract class InterstitialAd(context: Context) : AdObject(context) {


    override fun getAdType(): AdType {
        return AdType.Interstitial
    }


    data class Params(
        override val context: Context,
        override val adUnitId: String,
        override val adListener: AdListener?
    ) : BaseParams()
}