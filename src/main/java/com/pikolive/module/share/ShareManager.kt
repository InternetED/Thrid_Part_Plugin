package com.pikolive.module.share

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.pikolive.module.share.platform.CopyLinkSharePlatform
import com.pikolive.module.share.platform.InstagramSharePlatform
import com.pikolive.module.share.platform.LineSharePlatform
import com.pikolive.module.share.platform.MessengerSharePlatform
import com.pikolive.module.share.platform.OtherSharePlatform
import com.pikolive.module.share.platform.SharePlatform
import com.pikolive.module.share.platform.TelegramSharePlatform

/**
 * Creator: ED
 * Date: 2020/10/12 11:15 AM
 * Mail: salahayo3192@gmail.com
 *
 * **/

class ShareManager {


    fun openShareMenu(
        fragmentManager: FragmentManager,
        shareTitle: String,
        shareContent: String
    ) {
        ShareFragment().apply {

            this.arguments = Bundle().apply {
                putString(ShareFragment.BUNDLE_SHARE_TITLE, shareTitle)
                putString(ShareFragment.BUNDLE_SHARE_CONTENT, shareContent)
            }
        }.show(fragmentManager, "")

    }

    fun shareContent(context: Context, platformType: SharePlatformType, content: String) {
        findSharePlatform(platformType)
            .shareContent(context, content)

    }


    private fun findSharePlatform(platformType: SharePlatformType): SharePlatform {

        return when (platformType) {
            SharePlatformType.LINE -> LineSharePlatform()
            SharePlatformType.INSTAGRAM -> InstagramSharePlatform()
            SharePlatformType.MESSENGER -> MessengerSharePlatform()
            SharePlatformType.TELEGRAM -> TelegramSharePlatform()
            SharePlatformType.COPY_LINK -> CopyLinkSharePlatform()
            SharePlatformType.OTHER -> OtherSharePlatform()
        }
    }


    companion object {

        private val INSTANCE by lazy { ShareManager() }

        fun getInstance() = INSTANCE
    }
}