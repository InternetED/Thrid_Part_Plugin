package com.pikolive.module.webview.client

import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Message
import android.view.View
import android.webkit.*
import android.webkit.WebStorage.QuotaUpdater
import androidx.annotation.RequiresApi

/**
 * Creator: ED
 * Date: 2020/11/16 11:37 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
open class WebChromeClientDelegate(webChromeClient: WebChromeClient? = null) :
    WebChromeClient() {
    private var mDelegate: WebChromeClient? = null

    protected fun getDelegate(): WebChromeClient? {
        return mDelegate
    }

    fun setDelegate(delegate: WebChromeClient?) {
        mDelegate = delegate
    }

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
        if (mDelegate != null) {
            mDelegate!!.onProgressChanged(view, newProgress)
            return
        }
    }

    override fun onReceivedTitle(view: WebView?, title: String?) {
        if (mDelegate != null) {
            mDelegate!!.onReceivedTitle(view, title)
            return
        }
        super.onReceivedTitle(view, title)
    }

    override fun onReceivedIcon(view: WebView?, icon: Bitmap?) {
        if (mDelegate != null) {
            mDelegate!!.onReceivedIcon(view, icon)
            return
        }
        super.onReceivedIcon(view, icon)
    }

    override fun onReceivedTouchIconUrl(
        view: WebView?, url: String?,
        precomposed: Boolean
    ) {
        if (mDelegate != null) {
            mDelegate!!.onReceivedTouchIconUrl(view, url, precomposed)
            return
        }
        super.onReceivedTouchIconUrl(view, url, precomposed)
    }

    override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
        if (mDelegate != null) {
            mDelegate!!.onShowCustomView(view, callback)
            return
        }
        super.onShowCustomView(view, callback)
    }

    override fun onShowCustomView(
        view: View?, requestedOrientation: Int,
        callback: CustomViewCallback?
    ) {
        if (mDelegate != null) {
            mDelegate!!.onShowCustomView(view, requestedOrientation, callback)
            return
        }
        super.onShowCustomView(view, requestedOrientation, callback)
    }

    override fun onHideCustomView() {
        if (mDelegate != null) {
            mDelegate!!.onHideCustomView()
            return
        }
        super.onHideCustomView()
    }

    override fun onCreateWindow(
        view: WebView?, isDialog: Boolean,
        isUserGesture: Boolean, resultMsg: Message?
    ): Boolean {
        return if (mDelegate != null) {
            mDelegate!!.onCreateWindow(view, isDialog, isUserGesture, resultMsg)
        } else super.onCreateWindow(view, isDialog, isUserGesture, resultMsg)
    }

    override fun onRequestFocus(view: WebView?) {
        if (mDelegate != null) {
            mDelegate!!.onRequestFocus(view)
            return
        }
        super.onRequestFocus(view)
    }

    override fun onCloseWindow(window: WebView?) {
        if (mDelegate != null) {
            mDelegate!!.onCloseWindow(window)
            return
        }
        super.onCloseWindow(window)
    }

    override fun onJsAlert(
        view: WebView?, url: String?, message: String?,
        result: JsResult?
    ): Boolean {
        return if (mDelegate != null) {
            mDelegate!!.onJsAlert(view, url, message, result)
        } else super.onJsAlert(view, url, message, result)
    }

    override fun onJsConfirm(
        view: WebView?, url: String?, message: String?,
        result: JsResult?
    ): Boolean {
        return if (mDelegate != null) {
            mDelegate!!.onJsConfirm(view, url, message, result)
        } else super.onJsConfirm(view, url, message, result)
    }

    override fun onJsPrompt(
        view: WebView?, url: String?, message: String?,
        defaultValue: String?, result: JsPromptResult?
    ): Boolean {
        return if (mDelegate != null) {
            mDelegate!!.onJsPrompt(view, url, message, defaultValue, result)
        } else super.onJsPrompt(view, url, message, defaultValue, result)
    }

    override fun onJsBeforeUnload(
        view: WebView?, url: String?, message: String?,
        result: JsResult?
    ): Boolean {
        return if (mDelegate != null) {
            mDelegate!!.onJsBeforeUnload(view, url, message, result)
        } else super.onJsBeforeUnload(view, url, message, result)
    }

    @Deprecated("")
    override fun onExceededDatabaseQuota(
        url: String?, databaseIdentifier: String?,
        quota: Long, estimatedDatabaseSize: Long, totalQuota: Long,
        quotaUpdater: QuotaUpdater?
    ) {
        // This default implementation passes the current quota back to WebCore.
        // WebCore will interpret this that new quota was declined.
        if (mDelegate != null) {
            mDelegate!!.onExceededDatabaseQuota(
                url,
                databaseIdentifier,
                quota,
                estimatedDatabaseSize,
                totalQuota,
                quotaUpdater
            )
            return
        }
        super.onExceededDatabaseQuota(
            url,
            databaseIdentifier,
            quota,
            estimatedDatabaseSize,
            totalQuota,
            quotaUpdater
        )
    }

    override fun onGeolocationPermissionsShowPrompt(
        origin: String?,
        callback: GeolocationPermissions.Callback?
    ) {
        if (mDelegate != null) {
            mDelegate!!.onGeolocationPermissionsShowPrompt(origin, callback)
            return
        }
        super.onGeolocationPermissionsShowPrompt(origin, callback)
    }

    /**
     * notify the host application that a request for Geolocation permissions,
     * made with a previous call to
     * [onGeolocationPermissionsShowPrompt()][.onGeolocationPermissionsShowPrompt]
     * has been canceled. Any related UI should therefore be hidden.
     */
    override fun onGeolocationPermissionsHidePrompt() {
        if (mDelegate != null) {
            mDelegate!!.onGeolocationPermissionsHidePrompt()
            return
        }
        super.onGeolocationPermissionsHidePrompt()
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onPermissionRequest(request: PermissionRequest?) {
        if (mDelegate != null) {
            mDelegate!!.onPermissionRequest(request)
            return
        }
        super.onPermissionRequest(request)
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onPermissionRequestCanceled(request: PermissionRequest?) {
        if (mDelegate != null) {
            mDelegate!!.onPermissionRequestCanceled(request)
            return
        }
        super.onPermissionRequestCanceled(request)
    }

    override fun onJsTimeout(): Boolean {
        return if (mDelegate != null) {
            mDelegate!!.onJsTimeout()
        } else super.onJsTimeout()
    }

    @Deprecated("")
    override fun onConsoleMessage(message: String?, lineNumber: Int, sourceID: String?) {
        if (mDelegate != null) {
            mDelegate!!.onConsoleMessage(message, lineNumber, sourceID)
            return
        }
        super.onConsoleMessage(message, lineNumber, sourceID)
    }

    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        /*onConsoleMessage(consoleMessage.message(), consoleMessage.lineNumber(),
                consoleMessage.sourceId());*/
        return if (mDelegate != null) {
            mDelegate!!.onConsoleMessage(consoleMessage)
        } else super.onConsoleMessage(consoleMessage)
    }

    override fun getDefaultVideoPoster(): Bitmap? {
        return if (mDelegate != null) {
            mDelegate!!.defaultVideoPoster
        } else super.getDefaultVideoPoster()
    }

    override fun getVideoLoadingProgressView(): View? {
        return if (mDelegate != null) {
            mDelegate!!.videoLoadingProgressView
        } else super.getVideoLoadingProgressView()
    }

    override fun getVisitedHistory(callback: ValueCallback<Array<String?>?>?) {
        if (mDelegate != null) {
            mDelegate!!.getVisitedHistory(callback)
            return
        }
        super.getVisitedHistory(callback)
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onShowFileChooser(
        webView: WebView?, filePathCallback: ValueCallback<Array<Uri?>?>?,
        fileChooserParams: FileChooserParams?
    ): Boolean {
        return if (mDelegate != null) {
            mDelegate!!.onShowFileChooser(webView, filePathCallback, fileChooserParams)
        } else super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
    }

    /**
     * Android  >= 4.1
     *
     * @param uploadFile
     * @param acceptType
     * @param capture
     */
    open fun openFileChooser(uploadFile: ValueCallback<Uri?>, acceptType: String, capture: String) {
        commonRefect(
            mDelegate, "openFileChooser", arrayOf(uploadFile, acceptType, capture),
            ValueCallback::class.java,
            String::class.java,
            String::class.java
        )
    }

    /**
     * Android < 3.0
     *
     * @param valueCallback
     */
    open fun openFileChooser(valueCallback: ValueCallback<Uri?>) {
        commonRefect(
            mDelegate, "openFileChooser", arrayOf(valueCallback),
            ValueCallback::class.java
        )
    }

    /**
     * Android  >= 3.0
     *
     * @param valueCallback
     * @param acceptType
     */
    open fun openFileChooser(valueCallback: ValueCallback<*>, acceptType: String) {
        commonRefect(
            mDelegate, "openFileChooser", arrayOf(valueCallback, acceptType),
            ValueCallback::class.java,
            String::class.java
        )
    }

    private fun commonRefect(
        o: WebChromeClient?,
        mothed: String,
        os: Array<Any>,
        vararg clazzs: Class<*>
    ) {
        try {
            if (o == null) {
                return
            }
            val clazz: Class<*> = o.javaClass
            val mMethod = clazz.getMethod(mothed, *clazzs)
            mMethod.invoke(o, *os)
        } catch (ignore: Exception) {
            ignore.printStackTrace()
        }
    }

    init {
        mDelegate = webChromeClient
    }
}
