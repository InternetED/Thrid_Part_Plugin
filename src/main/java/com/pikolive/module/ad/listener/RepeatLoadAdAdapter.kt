package com.pikolive.module.ad.listener

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.pikolive.module.ad.type.AdObject

/**
 * Creator: ED
 * Date: 2020/5/26 12:19 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class RepeatLoadAdAdapter(
    private val repeatDelayMillis: Long = 3000L
) : AdListener {

    private val TAG = "RepeatLoadAdListener"


    override fun onAdLoaded(adObject: AdObject) {
        adObject.showAd()

    }

    override fun onAdFailedToLoad(adObject: AdObject, errorMessage: String) {
        Log.d(TAG, errorMessage)

        delayInvokeHandler.postDelayed({
            adObject.loadAd()
        }, repeatDelayMillis)
    }

    override fun onAdClick(adObject: AdObject) {
        Log.d(TAG, "onAdClick")
    }

    companion object {
        val delayInvokeHandler = Handler(Looper.getMainLooper())
    }

}