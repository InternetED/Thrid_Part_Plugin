package com.pikolive.module.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.lang.ref.WeakReference
import java.util.*

/**
 * Creator: ED
 * Date: 2020/4/21 2:57 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
internal class LifecycleCallbacksImpl : LifecycleCallbacks {

    override val activityStack = Stack<WeakReference<Activity>>()


    override fun bindApplication(application: Application) {
        application.registerActivityLifecycleCallbacks(this)
    }

    override fun unBindApplication(application: Application) {
        application.unregisterActivityLifecycleCallbacks(this)
    }


    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        activityStack.push(WeakReference(activity))
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        activityStack.pop().clear()
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityResumed(activity: Activity) {
    }
}