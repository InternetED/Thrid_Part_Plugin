package com.pikolive.module.ad.listener

import com.pikolive.module.ad.type.AdObject

/**
 * Creator: ED
 * Date: 2020/5/26 11:35 AM
 * Mail: salahayo3192@gmail.com
 *
 * 廣告監聽器
 * **/

interface AdListener {

    /**
     * 廣告加載成功
     * */
    fun onAdLoaded(adObject: AdObject)

    /**
     * 廣告加載失敗
     *
     * @param errorMessage 廣告加載失敗訊息
     * */
    fun onAdFailedToLoad(adObject: AdObject, errorMessage: String)


    /**
     * 廣告被點擊
     * */
    fun onAdClick(adObject: AdObject)

}