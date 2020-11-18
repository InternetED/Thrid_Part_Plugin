package com.pikolive.module.customer.update

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.pikolive.module.customer.dialog.createDialog
import com.pikolive.module.customer.update.data.DialogParams
import com.pikolive.module.customer.update.data.UpdateRequest
import top.limuyang2.ldialog.base.BaseLDialog
import top.limuyang2.ldialog.base.ViewHolder

/**
 * Creator: ED
 * Date: 2020/4/20 4:51 PM
 * Mail: salahayo3192@gmail.com
 *
 * * 步驟：
 * 1. 判斷是否有符合更新標準
 *  > 狀態分為：
 *      1. 弱更新：不強迫使用者更新，若用戶不更新照樣能使用 App
 *      2. 強更新：強迫使用者更新，若用戶不更新則關閉 App
 *
 * 2. 顯示訊息窗，提醒用戶更新 App，根據狀態（強｜弱更新）的不同，做出不同回饋
 *
 * 3. 跳轉至 Google Play Store，用戶自行更新
 * **/
interface UpdateAppManager {


    /**
     * 驗證是否需要更新
     *
     * @param context 上下文 [Context]
     * @param updateRequest 更新版本 [UpdateRequest]
     *
     * @return [UpdateState]
     * */
    fun needPromptUpdate(context: Context, updateRequest: UpdateRequest): UpdateState

    fun showUpdateDialog(
        updateState: UpdateState,
        fragmentManager: FragmentManager,
        dialogParams: DialogParams,
        positiveCallback: () -> Unit = {},
        negativeCallback: () -> Unit = {},
        afterViewHandlerListener: ((holder: ViewHolder, dialog: BaseLDialog<*>) -> Unit)? = null
    )
//
//    /**
//     * 顯示弱更新的 Dialog
//     *
//     * [createDialog]
//     * */
//    fun showWeakUpdateDialog(
//        fragmentManager: FragmentManager,
//        dialogParams: DialogParams,
//        positiveCallback: () -> Unit = {},
//        negativeCallback: () -> Unit = {},
//        afterViewHandlerListener: ((holder: ViewHolder, dialog: BaseLDialog<*>) -> Unit)? = null
//    )
//
//    /**
//     * 顯示強更新的 Dialog
//     *
//     * [createDialog]
//     * */
//    fun showStrongUpdateDialog(
//        fragmentManager: FragmentManager,
//        dialogParams: DialogParams,
//        positiveCallback: () -> Unit = {},
//        negativeCallback: () -> Unit = {},
//        afterViewHandlerListener: ((holder: ViewHolder, dialog: BaseLDialog<*>) -> Unit)? = null
//    )


    companion object {
        operator fun invoke(): UpdateAppManager {
            return UpdateAppManagerImpl()
        }
    }
}