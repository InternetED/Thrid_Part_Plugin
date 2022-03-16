package com.pikolive.module.webview.client

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import java.net.URISyntaxException
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


/**
 * Creator: ED
 * Date: 2020/12/2 2:16 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class DefaultWebViewClient(
    client: WebViewClient
) : WebViewClientDelegate(client) {
    /**
     * 系統可以處理的url正則
     */
    private val ACCEPTED_URI_SCHEME: Pattern = Pattern.compile(
        "(?i)"
                +  // switch on case insensitive matching
                '('
                +  // begin group for scheme
                "(?:http|https|ftp|file)://" + "|(?:inline|data|about|javascript):" + "|(?:.*:.*@)"
                + ')' + "(.*)"
    )


    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        if (shouldOverrideUrlLoadingByApp(view, url)) {
            return true
        }

        return super.shouldOverrideUrlLoading(view, url)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        if (shouldOverrideUrlLoadingByApp(view, request.url.toString())) {
            return true
        }
        return super.shouldOverrideUrlLoading(view, request)
    }

    private fun shouldOverrideUrlLoadingByApp(view: WebView, url: String): Boolean {
        if (isAcceptedScheme(url)) return false

        val intent: Intent
        intent = try {
            Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
        } catch (e: URISyntaxException) {
            Log.e("TAG", "URISyntaxException: " + e.localizedMessage)
            return true
        }

        intent.component = null
        intent.selector = null

        // 試著開啟商店安裝目標應用
        if (view.context.packageManager.resolveActivity(intent, 0) == null) {
            return tryHandleByMarket(view.context, intent.`package`)
        }

        try {
            view.context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Log.e("TAG", "ActivityNotFoundException: " + e.localizedMessage)
            return true
        }
        return true
    }

    /**
     * 該url是否屬於瀏覽器能處理的內部協議
     */
    private fun isAcceptedScheme(url: String): Boolean {
        //正則中忽略了大小寫，保險起見，還是轉成小寫
        val lowerCaseUrl = url.lowercase(Locale.getDefault())
        val acceptedUrlSchemeMatcher: Matcher = ACCEPTED_URI_SCHEME.matcher(lowerCaseUrl)
        return acceptedUrlSchemeMatcher.matches()
    }


    private fun tryHandleByMarket(context: Context, packageName: String?): Boolean {
        if (packageName != null) {
            val intent = Intent(
                Intent.ACTION_VIEW, Uri.parse(
                    "market://details?id="
                            + packageName
                )
            )
            try {
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Log.e("TAG", "tryHandleByMarket ActivityNotFoundException: " + e.localizedMessage)
            }
            return true
        }


        return false
    }
}