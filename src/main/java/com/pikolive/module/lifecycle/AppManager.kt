package com.pikolive.module.lifecycle

import android.app.Activity
import android.app.Application

/**
 * Creator: ED
 * Date: 2020/4/21 2:47 PM
 * Mail: salahayo3192@gmail.com
 *
 * 負責取得頂層的 Activity
 * **/

interface AppManager {

    fun bindApplication(application: Application)

    fun unBindApplication()

    fun getApplication(): Application

    fun getTopActivity(): Activity

    companion object {
        fun getInstance() = Inner.instance
    }

    private class Inner {
        companion object {
            val instance: AppManager = AppManagerImpl()
        }

    }
}
