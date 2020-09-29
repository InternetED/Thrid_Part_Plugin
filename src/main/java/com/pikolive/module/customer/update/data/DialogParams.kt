package com.pikolive.module.customer.update.data

/**
 * Creator: ED
 * Date: 2020/6/24 2:12 PM
 * Mail: salahayo3192@gmail.com
 *
 * @param title Dialog 標題
 * @param message Dialog 內容
 * @param positiveText Dialog 正向按鈕文本
 * @param negativeText Dialog 負向按鈕文本
 * **/
data class DialogParams(
    val title: String,
    val message: String,
    val positiveText: String = "OK",
    val negativeText: String = "取消"
)

