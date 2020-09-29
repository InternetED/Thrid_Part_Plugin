package com.pikolive.module.customer.update

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Creator: ED
 * Date: 2020/4/20 5:19 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
@Parcelize
enum class UpdateState : Parcelable {
    /**
     * 不需要更新
     * */
    NOTHING,

    /**
     * 弱更新
     * */
    WEAK,

    /**
     * 強制更新
     * */
    STRONG
}