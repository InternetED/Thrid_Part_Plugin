package com.pikolive.module.login.manager.loginPlatform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.internet.boy.androidbase.kutils.logd
import com.linecorp.linesdk.Scope
import com.linecorp.linesdk.api.LineApiClient
import com.linecorp.linesdk.api.LineApiClientBuilder
import com.linecorp.linesdk.auth.LineAuthenticationParams
import com.linecorp.linesdk.auth.LineLoginApi
import com.pikolive.module.R
import com.pikolive.module.lifecycle.AppManager
import com.pikolive.module.login.manager.LoginPlatformProvide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 *@date: 2019/3/13 - 上午 09:59
 *@author: Ed
 *@email: salahayo3192@gmail.com
 **/
class LineLoginPlatform : LoginPlatform {


    private var callback: LoginPlatform.LogInCallback? = null

    private val lineChannelId: String =
        AppManager.getInstance().getApplication().getString(R.string.login_line_channelId)

    init {

        // 檢查是否有設定 login_google_server_id 若無則拋出錯誤
        if (lineChannelId.isEmpty())
            throw IllegalAccessError("尚未註冊 Ling channel id，請至 res/values/strings 中設定 login_line_channelId")

    }

    override fun logIn(activity: AppCompatActivity, callback: LoginPlatform.LogInCallback) {

        val loginIntent = LineLoginApi.getLoginIntent(
            activity, lineChannelId, LineAuthenticationParams.Builder()
                .scopes(arrayListOf(Scope.PROFILE))
                .build()
        )

        this.callback = callback

        activity.startActivityForResult(loginIntent, LoginPlatformProvide.LINE)

    }


    override fun logOut(activity: AppCompatActivity, callback: LoginPlatform.LogOutCallback) {
        val lineApiClient = LineApiClientBuilder(activity, lineChannelId)
            .build()

        GlobalScope.launch(Dispatchers.IO) {
            val lineApiResponse = lineApiClient.logout()

            if (lineApiResponse.isSuccess) {
                logd("Line logout has success.")
                callback.onSuccess()
            } else {
                logd("Line logout has failure. error：${lineApiResponse.errorData}")
                callback.onFailure()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result = LineLoginApi.getLoginResultFromIntent(data)



        if (result.isSuccess) {
            // 登入成功

            val token = result.lineCredential?.accessToken?.tokenString ?: ""
            logd("Line login has success. token：$token")

            callback?.onSuccess(token)

        } else {
            // 登入失敗
            logd("Line login has failure. error：${result.errorData}")
        }

        callback = null // 移除引用

    }

}