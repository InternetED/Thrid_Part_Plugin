package com.pikolive.module.login.manager.loginPlatform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginBehavior
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.internet.boy.androidbase.kutils.logd
import com.pikolive.module.R
import com.pikolive.module.lifecycle.AppManager

/**
 *@date: 2019/3/13 - 上午 10:08
 *@author: Ed
 *@email: salahayo3192@gmail.com
 **/
class FacebookLoginPlatform : LoginPlatform {


    private val loginManager by lazy { LoginManager.getInstance() }
    private val callbackManager by lazy {
        CallbackManager.Factory.create()
    }


    init {
        // 檢查是否有設定 login_facebook_applicationId 若無則拋出錯誤
        val applicationContext = AppManager.getInstance().getApplication()

        val facebookApplicationId =
            applicationContext.getString(R.string.login_facebook_applicationId)
        if (facebookApplicationId.isEmpty())
            throw IllegalAccessError("尚未註冊 Facebook application id，請至 res/values/strings 中設定 login_facebook_applicationId")
    }


    override fun logIn(activity: AppCompatActivity, callback: LoginPlatform.LogInCallback) {
        val currentAccessToken = AccessToken.getCurrentAccessToken()
        if (currentAccessToken != null && !currentAccessToken.isExpired) {

            logd("Facebook has duble login. token：$currentAccessToken, isExpired：${currentAccessToken.isExpired}")

            // 避免重複登入
            logOut(activity, object : LoginPlatform.LogOutCallback {
                override fun onSuccess() {
                    logIn(activity, callback)
                }

                override fun onFailure() {
                    logIn(activity, callback)
                }

            })
            return
        }



        loginManager.loginBehavior = LoginBehavior.NATIVE_WITH_FALLBACK

        loginManager.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                val token = result.accessToken.token

                logd("Facebook login has success. token：$token")
                callback.onSuccess(token)
            }

            override fun onCancel() {
                logd("Facebook login has cancel.")
            }

            override fun onError(error: FacebookException) {
                logd("Facebook login has error. error：${error}")

                callback.onFailure()
            }

        })

        loginManager.logInWithReadPermissions(activity, arrayListOf("public_profile"))

    }


    override fun logOut(activity: AppCompatActivity, callback: LoginPlatform.LogOutCallback) {
        loginManager.logOut()
        callback.onSuccess()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

}