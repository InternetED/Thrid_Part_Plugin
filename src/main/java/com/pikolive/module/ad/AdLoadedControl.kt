package com.pikolive.module.ad

/**
 * Creator: ED
 * Date: 2020/5/27 10:51 AM
 * Mail: salahayo3192@gmail.com
 *
 * **/
interface AdLoadedControl {

    /**
     * 設定廣告是否成功加載
     *
     * @param isLoaded 成功與否
     * */
    fun setLoaded(isLoaded: Boolean)
}