package com.pikolive.module.ad

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.facebook.ads.AdSettings
import com.facebook.ads.AudienceNetworkAds
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.pikolive.module.BuildConfig

/**
 * Creator: ED
 * Date: 2020/5/26 2:10 PM
 * Mail: salahayo3192@gmail.com
 *
 * 進行各平台的廣告初始化加載
 * **/

class AdManagerProvider : ContentProvider() {

    private val TAG = this::class.java.simpleName

    override fun onCreate(): Boolean {

        admobInit()


        fbinit()

        return true
    }

    //    ca-app-pub-3940256099942544/1033173712 插頁式廣告
//    ca-app-pub-3940256099942544/8691691433 插頁式視頻廣告
    private fun admobInit() {
        val requestConfiguration =
            RequestConfiguration.Builder()
                .apply {
                    if (BuildConfig.DEBUG) {
                        setTestDeviceIds(
                            listOf(
                                AdRequest.DEVICE_ID_EMULATOR
                            )
                        )

                    }
                }
                .build()
        MobileAds.setRequestConfiguration(requestConfiguration)


        MobileAds.initialize(context?.applicationContext)
    }

    private fun fbinit() {
        AdSettings.setIntegrationErrorMode(AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CALLBACK_MODE)
        AudienceNetworkAds.buildInitSettings(context)
            .withInitListener {
                Log.d(TAG, "Facebook 廣告初始化是否成功：${it.isSuccess}，訊息為：${it.message}")
            }
            .initialize()


        if (BuildConfig.DEBUG) {
            AdSettings.addTestDevice("C83840F79BFB10E6959D1B57BD94194C")
            AdSettings.setTestMode(true)// for get test ad in your device
            AdSettings.setDebugBuild(true)
        }

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