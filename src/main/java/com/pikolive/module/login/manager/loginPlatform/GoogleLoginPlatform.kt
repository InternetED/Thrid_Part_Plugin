package com.pikolive.module.login.manager.loginPlatform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.internet.boy.androidbase.kutils.logd
import com.pikolive.module.R
import com.pikolive.module.lifecycle.AppManager
import com.pikolive.module.login.manager.LoginPlatformProvide


/**
 *@date: 2019/3/13 - 上午 10:08
 *@author: Ed
 *@email: salahayo3192@gmail.com
 **/
class GoogleLoginPlatform : LoginPlatform {


    private var callback: LoginPlatform.LogInCallback? = null

    private val gso: GoogleSignInOptions


    init {

        // 檢查是否有設定 login_google_server_id 若無則拋出錯誤
        val serverAuthCode =
            AppManager.getInstance().getApplication().getString(R.string.login_google_serverId)
        if (serverAuthCode.isEmpty())
            throw IllegalAccessError("尚未註冊 Google Server id，請至 res/values/strings 中設定 login_google_serverId")

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(serverAuthCode)
            .requestProfile()
            .build()
    }

    override fun logIn(activity: AppCompatActivity, callback: LoginPlatform.LogInCallback) {


        val googleSignIntent = GoogleSignIn.getClient(activity, gso).signInIntent


        this.callback = callback

        activity.startActivityForResult(googleSignIntent, LoginPlatformProvide.GOOGLE)
    }

    override fun logOut(activity: AppCompatActivity, callback: LoginPlatform.LogOutCallback) {

        GoogleSignIn.getClient(activity, gso)
            .signOut()
            .addOnSuccessListener {
                logd("Google logout has success.")
                callback.onSuccess()
            }
            .addOnFailureListener {
                logd("Google logout has failure. error：$it")
                callback.onFailure()
            }
            .addOnCanceledListener {
                logd("Google logout has cancel.")
            }
            .addOnCompleteListener {
                logd("Google logout has complete.")
            }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val signedInAccountFromIntent = GoogleSignIn.getSignedInAccountFromIntent(data)

        signedInAccountFromIntent
            .addOnSuccessListener {
                callback?.onSuccess(it.serverAuthCode ?: "")
                logd("Google login has success. token：${it.serverAuthCode}")
            }
            .addOnFailureListener {
                logd("Google login has failure. error：${it}")
                callback?.onFailure()
            }
            .addOnCanceledListener {
                logd("Google login has cancel.")
            }
            .addOnCompleteListener {
                logd("Google login has complete.")
                callback = null // 移除引用
            }

    }

}