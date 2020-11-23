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
        val FACEBOOK = CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode() // 64206
        const val LINE = 500
    }

    private val platformLazyMap = mapOf(
        FACEBOOK to lazyOf(FacebookLoginPlatform()),
        LINE to lazyOf(LineLoginPlatform()),
        GOOGLE to lazyOf(GoogleLoginPlatform())
    )


    fun getLoginPlatform(type: Int): LoginPlatform {

        return (platformLazyMap[type] ?: error("")).value
    }


}
