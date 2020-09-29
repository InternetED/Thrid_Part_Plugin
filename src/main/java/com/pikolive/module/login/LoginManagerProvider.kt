package com.pikolive.module.login

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.facebook.FacebookSdk

/**
 * Creator: ED
 * Date: 2020/5/26 2:10 PM
 * Mail: salahayo3192@gmail.com
 *
 * 進行各平台的廣告初始化加載
 * **/

class LoginManagerProvider : ContentProvider() {
    override fun onCreate(): Boolean {

        FacebookSdk.sdkInitialize(context?.applicationContext)
//        AppEventsLogger.activateApp(context?.applicationContext)

        return true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }
}