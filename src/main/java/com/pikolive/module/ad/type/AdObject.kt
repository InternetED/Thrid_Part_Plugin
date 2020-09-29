package com.pikolive.module.ad.type

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.pikolive.module.ad.enums.AdType
import com.pikolive.module.ad.listener.AdListener

/**
 * Creator: ED
 * Date: 2020/5/26 9:50 AM
 * Mail: salahayo3192@gmail.com
 *
 * 廣告的基礎類
 * **/
abstract class AdObject(
    protected val mContext: Context
) : LifecycleObserver {


    init {
        listenToLife()
    }

    /**
     * 監聽 context 的生命週期，在 context 銷毀時，回收 bannerAd
     * */
    private fun listenToLife() {
        when (mContext) {
            is FragmentActivity -> mContext.lifecycle.addObserver(this)
            is Fragment -> mContext.lifecycle.addObserver(this)
            else -> {
                Log.w("BannerAd Warning", "將無法監控 ${javaClass.simpleName} 的生命週期，可能造成內存泄露")
            }
        }
    }

    /**
     * 設定廣告版位的 ID
     * */
    internal abstract fun setAdUnitId(adUnitId: String)

    /**
     * 設定廣告加載的監聽器
     * */
    internal abstract fun setListener(listener: AdListener?)

    /**
     * 廣告是否加載成功
     * */
    abstract fun isLoadedAd(): Boolean

    /**
     * 廣告加載
     * */
    abstract fun loadAd()

    /**
     * 顯示廣告
     * */
    abstract fun showAd()


    /**
     * 廣告的類型
     *
     * @see AdType
     * */
    abstract fun getAdType(): AdType


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {

    }


    abstract class BaseParams {
        abstract val context: Context
        abstract val adUnitId: String
        abstract val adListener: AdListener?
    }
}