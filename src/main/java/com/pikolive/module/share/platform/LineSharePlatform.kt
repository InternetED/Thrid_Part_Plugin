package com.pikolive.module.share.platform

import android.content.ComponentName
import android.content.Context

/**
 * Creator: ED
 * Date: 2020/10/12 5:48 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class LineSharePlatform : SharePlatform {
    override fun shareContent(context: Context, content: String) {
        val cn = ComponentName(
            "jp.naver.line.android",
            "jp.naver.line.android.activity.selectchat.SelectChatActivityLaunchActivity"
        )

        val intent = createShareIntent("jp.naver.line.android", content)
            .apply { component = cn }


        startShareIntent(context, intent, "Line")
    }
}