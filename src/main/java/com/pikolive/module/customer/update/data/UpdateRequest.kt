package com.pikolive.module.customer.update.data

/**
 * Creator: ED
 * Date: 2020/4/20 5:16 PM
 * Mail: salahayo3192@gmail.com
 *
 * App 更新判斷
 *
 * 理論上 strangeVersion 應小於 weakVersion
 * **/
data class UpdateRequest(
    val weakVersion : Int,
    val strangeVersion : Int
)