package com.pikolive.module.customer.update

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.pikolive.module.customer.dialog.createDialog
import com.pikolive.module.customer.update.data.DialogParams
import com.pikolive.module.customer.update.data.UpdateRequest
import top.limuyang2.ldialog.base.BaseLDialog
import top.limuyang2.ldialog.base.ViewHolder


internal class UpdateAppManagerImpl : UpdateAppManager {




    override fun needPromptUpdate(context: Context, updateRequest: UpdateRequest): UpdateState {

        return validateRequireUpdate(context, updateRequest.weakVersion, updateRequest.strangeVersion)
    }

    override fun showUpdateDialog(
        updateState: UpdateState,
        fragmentManager: FragmentManager,
        dialogParams: DialogParams,
        positiveCallback: () -> Unit,
        negativeCallback: () -> Unit,
        afterViewHandlerListener: ((holder: ViewHolder, dialog: BaseLDialog<*>) -> Unit)?
    ) {
        when (updateState) {
            UpdateState.STRONG -> showStrongUpdateDialog(
                fragmentManager,
                dialogParams,
                positiveCallback,
                negativeCallback,
                afterViewHandlerListener
            )
            UpdateState.WEAK -> showWeakUpdateDialog(
                fragmentManager,
                dialogParams,
                positiveCallback,
                negativeCallback,
                afterViewHandlerListener
            )
            UpdateState.NOTHING -> return

        }
    }

    private fun showWeakUpdateDialog(
        fragmentManager: FragmentManager,
        dialogParams: DialogParams,
        positiveCallback: () -> Unit,
        negativeCallback: () -> Unit,
        afterViewHandlerListener: ((holder: ViewHolder, dialog: BaseLDialog<*>) -> Unit)?
    ) {
        createDialog(fragmentManager, dialogParams, positiveCallback, negativeCallback,afterViewHandlerListener)
            .show()
    }


    private fun showStrongUpdateDialog(
        fragmentManager: FragmentManager,
        dialogParams: DialogParams,
        positiveCallback: () -> Unit,
        negativeCallback: () -> Unit,
        afterViewHandlerListener: ((holder: ViewHolder, dialog: BaseLDialog<*>) -> Unit)?
    ) {
        createDialog(fragmentManager, dialogParams, positiveCallback, negativeCallback,afterViewHandlerListener)
            .setCancelableAll(false)
            .setCancelableOutside(false)
            .show()
    }


    private fun validateRequireUpdate(
        context: Context,
        weakVersion: Int,
        strangeVersion: Int
    ): UpdateState {

        val curVersion = getAppVersion(context)

        return when {
            curVersion <= strangeVersion -> UpdateState.STRONG
            curVersion <= weakVersion -> UpdateState.WEAK
            else -> UpdateState.NOTHING
        }
    }

    private fun getAppVersion(context: Context): Long {

        return context.packageManager.getPackageInfo(context.packageName, 0)?.versionCode?.toLong()
            ?: 0L
    }
}