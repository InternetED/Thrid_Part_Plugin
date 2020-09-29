package com.pikolive.module.lifecycle

import android.app.Application
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

/**
 * Creator: ED
 * Date: 2020/4/22 11:35 AM
 * Mail: salahayo3192@gmail.com
 *
 * 通過 ContentProvider 對 AppManager 設定 Application
 * **/
internal class AppManagerProvider : ContentProvider() {
    override fun onCreate(): Boolean {
        AppManager.getInstance().bindApplication(context?.applicationContext as Application)
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