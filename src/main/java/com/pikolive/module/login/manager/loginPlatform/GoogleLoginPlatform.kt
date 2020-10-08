package com.pikolive.module.login.manager.loginPlatform

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.internet.boy.androidbase.kutils.logd
import com.pikolive.module.login.manager.LoginPlatformProvide


/**
 *@date: 2019/3/13 - 上午 10:08
 *@author: Ed
 *@email: salahayo3192@gmail.com
 **/
class GoogleLoginPlatform : LoginPlatform {

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    private var callback: LoginPlatform.LogInCallback? = null


    private val gso by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(SERVER_AUTH_CODE)
            .requestEmail()
            .build()
    }


    override fun logIn(activity: AppCompatActivity, callback: LoginPlatform.LogInCallback) {

        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso)

        val googleSignIntent = mGoogleSignInClient.signInIntent

        activity.startActivityForResult(googleSignIntent, LoginPlatformProvide.GOOGLE)

        this.callback = callback
    }

    override fun logOut(activity: AppCompatActivity, callback: LoginPlatform.LogOutCallback) {

        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso)
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


    companion object {

        const val SERVER_AUTH_CODE =
            "1004800572541-d8g7orjksghbove3qbfouvldc4q0itmt.apps.googleusercontent.com"


    }

}