package com.pikolive.module.customer.dialog

import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.pikolive.module.R
import com.pikolive.module.customer.update.data.DialogParams
import top.limuyang2.ldialog.LDialog
import top.limuyang2.ldialog.base.BaseLDialog
import top.limuyang2.ldialog.base.ViewHandlerListener
import top.limuyang2.ldialog.base.ViewHolder

/**
 * Creator: ED
 * Date: 2020/6/30 11:11 AM
 * Mail: salahayo3192@gmail.com
 *
 * **/


/**
 * 創建基本的 FragmentDialog
 *
 * @param fragmentManager [FragmentManager]
 * @param dialogParams Dialog 基本屬性，[DialogParams]
 * @param positiveCallback 點擊正向按鈕的 Callback
 * @param negativeCallback 點擊負向按鈕的 Callback
 * @param afterViewHandlerListener 於 ViewHandlerListener 設定之後的 Callback
 *
 * @return [LDialog]
 * */
fun createDialog(
    fragmentManager: FragmentManager,
    dialogParams: DialogParams,
    positiveCallback: () -> Unit,
    negativeCallback: () -> Unit,
    afterViewHandlerListener: ((holder: ViewHolder, dialog: BaseLDialog<*>) -> Unit)? = null
): LDialog {
    return LDialog.init(fragmentManager)
        .setLayoutRes(R.layout.dialog_common)
        .setWidthScale(0.8F)
        .setBackgroundDrawableRes(R.drawable.def_dialog_bg)
        .setViewHandlerListener(object : ViewHandlerListener() {
            override fun convertView(holder: ViewHolder, dialog: BaseLDialog<*>) {

                val tvDialogTitle = holder.getView<TextView>(R.id.tvDialogTitle)
                val tvDialogContext = holder.getView<TextView>(R.id.tvDialogContent)
                val tvPositive = holder.getView<TextView>(R.id.tvPositive)
                val tvNegative = holder.getView<TextView>(R.id.tvNegative)


                tvDialogTitle.text = dialogParams.title
                tvDialogContext.text = dialogParams.message
                tvPositive.text = dialogParams.positiveText
                tvNegative.text = dialogParams.negativeText


                tvPositive.setOnClickListener {
                    positiveCallback()
                    dialog.dismiss()
                }

                tvNegative.setOnClickListener {
                    negativeCallback()
                    dialog.dismiss()
                }

                afterViewHandlerListener?.invoke(holder, dialog)
            }
        })
}