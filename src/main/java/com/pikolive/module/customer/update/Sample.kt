package com.pikolive.module.customer.update

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.pikolive.module.customer.update.data.DialogParams
import com.pikolive.module.customer.update.data.UpdateRequest

/**
 * Creator: ED
 * Date: 2020/4/22 3:58 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/


fun sample(context: Context, fragmentManager: FragmentManager) {
    val updateManager: UpdateAppManager = UpdateAppManager()

    val needPromptUpdate = updateManager.needPromptUpdate(
        context,
        UpdateRequest(2, 1)
    )
    val dialogParams = DialogParams("標題", "內容")

    when (needPromptUpdate) {
        UpdateState.STRONG -> {
            updateManager.showStrongUpdateDialog(fragmentManager, dialogParams)
        }
        UpdateState.WEAK -> {
            updateManager.showWeakUpdateDialog(fragmentManager, dialogParams)
        }
        UpdateState.NOTHING -> {
            // do nothing
        }
    }

//    val title = "更新標題"
//    val message = "更新訊息"
//    val positiveButtonText = "前往"
//    var negativeButtonText: String? = null
//
//    var cancelOnTouchOutside = true
//    var canceled = true
//
//    when (needPromptUpdate) {
//        UpdateState.WEAK -> {
//            negativeButtonText = "取消"
//
//        }
//        UpdateState.STRONG -> {
//            cancelOnTouchOutside = false
//            canceled = false
//        }
//        else -> {
//            return
//        }
//    }

//    DialogParams(
//        title = title,
//        message = message,
////                title = null,
////                message = null,
//        layoutResId = R.layout.dialog_update_prompt,
//        viewHandler = object : DialogCallback() {
//            override fun callback(dialog: MaterialDialog) {
////                        dialog.findViewById<ImageView>(R.id.ivImage).background = ColorDrawable(
////                            ContextCompat.getColor(
////                                this@MainActivity,
////                                R.color.colorAccent
////                            )
////                        )
//            }
//
//        },
//        positiveButtonText = positiveButtonText,
//        negativeButtonText = negativeButtonText,
////                positiveButtonText = null,
////                negativeButtonText = null,
//        cancelOnTouchOutside = cancelOnTouchOutside,
//        canceled = canceled,
//        positiveHandler = object : DialogCallback() {
//            override fun callback(dialog: MaterialDialog) {
//            }
//
//        }
//    ).apply {
//        updateManager.showPromptUpdateDialog(context, this)
//
//    }
}
