package com.pikolive.module.share

import androidx.annotation.DrawableRes

/**
 * Creator: ED
 * Date: 2020/10/12 2:11 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
data class ShareItem(@DrawableRes val iconDrawableRes: Int,val text :String, val type: SharePlatformType)