package com.pikolive.module.lifecycle

import android.app.Activity
import android.app.Application

/**
 * Creator: ED
 * Date: 2020/4/21 3:10 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/

internal class AppManagerImpl : AppManager {

    private val lifecycleCallbacks: LifecycleCallbacks =
        LifecycleCallbacksImpl()

    private lateinit var application: Application

    override fun bindApplication(application: Application) {
        this.application = application
        lifecycleCallbacks.bindApplication(application)
    }

    override fun unBindApplication() {
        lifecycleCallbacks.unBindApplication(application)
    }

    override fun getApplication(): Application {
        return application
    }

    override fun getTopActivity(): Activity {
        return lifecycleCallbacks.activityStack.peek().get()!! // 將空錯誤拋出去
    }
}
