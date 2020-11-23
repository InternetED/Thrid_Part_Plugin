package com.pikolive.module.login.manager.loginPlatform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
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

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    private var callback: LoginPlatform.LogInCallback? = null

    private var gso: GoogleSignInOptions? = null


    override fun logIn(activity: AppCompatActivity, callback: LoginPlatform.LogInCallback) {


        mGoogleSignInClient = GoogleSignIn.getClient(activity, getGoogleSignInOptions())

        val googleSignIntent = mGoogleSignInClient.signInIntent

        activity.startActivityForResult(googleSignIntent, LoginPlatformProvide.GOOGLE)

        this.callback = callback
    }

    override fun logOut(activity: AppCompatActivity, callback: LoginPlatform.LogOutCallback) {

        mGoogleSignInClient = GoogleSignIn.getClient(activity, getGoogleSignInOptions())
        val signOut = mGoogleSignInClient.signOut()

        signOut.addOnSuccessListener {
            callback.onSuccess()
        }
        signOut.addOnFailureListener {
            callback.onFailure()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val signedInAccountFromIntent = GoogleSignIn.getSignedInAccountFromIntent(data)

        signedInAccountFromIntent.addOnSuccessListener {
            val token = it.serverAuthCode ?: ""
            callback?.onSuccess(token)
            callback = null
            logd("GOOGLE_TOKEN : $token", "getAccessToken")
        }

        signedInAccountFromIntent.addOnFailureListener {
            callback?.onFailure()
            callback = null
            logd("$it", "getAccessToken")

        }

    }

    private fun getGoogleSignInOptions(): GoogleSignInOptions {
        if (gso == null) {
            val serverAuthCode =
                AppManager.getInstance().getApplication().getString(R.string.google_server_id)
            if (serverAuthCode.isEmpty())
                throw IllegalAccessError("尚未註冊 Google Server id，請至 res/values/strings 中設定 google_server_id")

            gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestServerAuthCode(serverAuthCode)
                .requestEmail()
                .build()
        }

        return gso!!
    }

}