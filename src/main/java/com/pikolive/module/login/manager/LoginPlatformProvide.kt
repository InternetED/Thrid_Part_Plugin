package com.pikolive.module.login.manager

import com.facebook.internal.CallbackManagerImpl
import com.pikolive.module.login.manager.loginPlatform.FacebookLoginPlatform
import com.pikolive.module.login.manager.loginPlatform.GoogleLoginPlatform
import com.pikolive.module.login.manager.loginPlatform.LineLoginPlatform
import com.pikolive.module.login.manager.loginPlatform.LoginPlatform

/**
 *@date: 2019/4/18 - 上午 10:15
 *@author: Ed
 *@email: salahayo3192@gmail.com
 **/
class LoginPlatformProvide {
    companion object {
        const val GOOGLE = 300
        val FACEBOOK = CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode()
        const val LINE = 500
    }

//    private val cacheSize = (Runtime.getRuntime().maxMemory() / 8 / 2).toInt()

//    private val lruCacheLoginPlatform = object : LruCache<Int, LoginPlatform>(cacheSize) {
//        override fun create(key: Int): LoginPlatform {
//            return when (key) {
//                FACEBOOK -> FacebookLoginPlatform()
//                LINE -> LineLoginPlatform()
//                GOOGLE -> GoogleLoginPlatform()
//                else -> throw IllegalAccessError()
//            }
//        }
//    }

    private val platformLazyMap = mapOf(
        FACEBOOK to lazyOf(FacebookLoginPlatform()),
        LINE to lazyOf(LineLoginPlatform()),
        GOOGLE to lazyOf(GoogleLoginPlatform())
    )


    fun getLoginPlatform(type: Int): LoginPlatform {

        return (platformLazyMap[type] ?: error("")).value
    }


}
