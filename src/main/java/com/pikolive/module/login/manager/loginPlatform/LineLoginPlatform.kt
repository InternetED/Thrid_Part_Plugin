package com.pikolive.module.login.manager.loginPlatform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.internet.boy.androidbase.kutils.logd
import com.linecorp.linesdk.Scope
import com.linecorp.linesdk.api.LineApiClient
import com.linecorp.linesdk.api.LineApiClientBuilder
import com.linecorp.linesdk.auth.LineAuthenticationParams
import com.linecorp.linesdk.auth.LineLoginApi
import com.pikolive.module.login.manager.LoginPlatformProvide

/**
 *@date: 2019/3/13 - 上午 09:59
 *@author: Ed
 *@email: salahayo3192@gmail.com
 **/
class LineLoginPlatform : LoginPlatform {

    private lateinit var mLineApiClient: LineApiClient

    private var callback: LoginPlatform.LogInCallback? = null

    override fun logOut(activity: AppCompatActivity, callback: LoginPlatform.LogOutCallback) {
        val apiClientBuilder = LineApiClientBuilder(activity, LINE_CHANNEL_ID)
        mLineApiClient = apiClientBuilder.build()

        Thread(Runnable {

            val lineApiResponse = mLineApiClient.logout()

            if (lineApiResponse.isSuccess) {
                callback.onSuccess()
            } else {
                callback.onFailure()
            }
        }).start()

    }


    override fun logIn(activity: AppCompatActivity, callback: LoginPlatform.LogInCallback) {

        val loginIntent = LineLoginApi.getLoginIntent(
            activity, LINE_CHANNEL_ID, LineAuthenticationParams.Builder()
                .scopes(arrayListOf(Scope.PROFILE))
                .build()
        )

        activity.startActivityForResult(loginIntent, LoginPlatformProvide.LINE)

        this.callback = callback

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result = LineLoginApi.getLoginResultFromIntent(data)


        val token = if (result.isSuccess) {
            result.lineCredential?.accessToken?.tokenString
        } else {
            logd("${result.errorData}", "getAccessToken")
            null
        }

        if (token != null) {
            callback?.onSuccess(token)
        } else {
            callback?.onFailure()
        }
        callback = null

        logd("LINE_TOKEN : $token", "getAccessToken")
    }

    companion object {
        const val LINE_CHANNEL_ID = "1654354447"

    }

}