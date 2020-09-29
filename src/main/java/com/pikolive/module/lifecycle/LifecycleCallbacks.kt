package com.pikolive.module.lifecycle

import android.app.Activity
import android.app.Application
import java.lang.ref.WeakReference
import java.util.*

/**
 * Creator: ED
 * Date: 2020/4/21 2:57 PM
 * Mail: salahayo3192@gmail.com
 *
 * 負責管理所有此應用的 Activities，將之存於 activityStack
 * **/
interface LifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    val activityStack: Stack<WeakReference<Activity>>

    fun bindApplication(application: Application)

    fun unBindApplication(application: Application)

}