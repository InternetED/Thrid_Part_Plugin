package com.pikolive.module.ad.type

import android.content.Context
import android.view.ViewGroup
import com.pikolive.module.ad.AdLoadedControl
import com.pikolive.module.ad.enums.AdType
import com.pikolive.module.ad.listener.AdListener

/**
 * Creator: ED
 * Date: 2020/5/26 11:42 AM
 * Mail: salahayo3192@gmail.com
 *
 * 橫幅廣告
 * **/
abstract class BannerAd(
    context: Context,
    protected val viewGroup: ViewGroup
) : AdObject(context), AdLoadedControl {

    private var isLoaded: Boolean = false


    override fun setLoaded(isLoaded: Boolean) {
        this.isLoaded = isLoaded
    }

    override fun isLoadedAd(): Boolean {
        return isLoaded
    }

    abstract fun setAdSize(adSize: AdSize)

    override fun getAdType(): AdType {
        return AdType.Banner
    }


    data class Params(
        override val context: Context,
        val adContainer: ViewGroup,
        val adSize: AdSize,
        override val adUnitId: String,
        override val adListener: AdListener?
    ) : BaseParams()


    enum class AdSize {
        MIDDLE, SMALL
    }
}