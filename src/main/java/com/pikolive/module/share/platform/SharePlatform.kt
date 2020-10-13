package com.pikolive.module.share.platform

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import com.internet.boy.androidbase.toast

/**
 * Creator: ED
 * Date: 2020/10/12 5:48 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
interface SharePlatform {
    fun shareContent(context: Context, content: String)

    fun createShareIntent(packageName: String, content: String): Intent {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.putExtra(Intent.EXTRA_TEXT, content)
        shareIntent.type = "text/plain"
        shareIntent.setPackage(packageName)

        return shareIntent
    }

    fun startShareIntent(context: Context, shareIntent: Intent, platformName: String) {
        kotlin.runCatching {
            context.startActivity(shareIntent)
        }.apply {
            val throwable = this.exceptionOrNull()
            if (throwable != null && throwable is ActivityNotFoundException) {
                context.toast("Please Install $platformName.")
            }
        }
    }
}