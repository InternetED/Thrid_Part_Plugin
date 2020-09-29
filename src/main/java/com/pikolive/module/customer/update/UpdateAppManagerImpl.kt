package com.pikolive.module.customer.update

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.pikolive.module.customer.dialog.createDialog
import com.pikolive.module.customer.update.data.DialogParams
import com.pikolive.module.customer.update.data.UpdateRequest


internal class UpdateAppManagerImpl : UpdateAppManager {


    private lateinit var mUpdateState: UpdateState


    override fun needPromptUpdate(context: Context, updateRequest: UpdateRequest): UpdateState {
        mUpdateState =
            validateRequireUpdate(context, updateRequest.weakVersion, updateRequest.strangeVersion)

        return mUpdateState
    }

    override fun showUpdateDialog(
        updateState: UpdateState,
        fragmentManager: FragmentManager,
        dialogParams: DialogParams,
        positiveCallback: () -> Unit,
        negativeCallback: () -> Unit
    ) {
        when (updateState) {
            UpdateState.STRONG -> showStrongUpdateDialog(
                fragmentManager,
                dialogParams,
                positiveCallback,
                negativeCallback
            )
            UpdateState.WEAK -> showWeakUpdateDialog(
                fragmentManager,
                dialogParams,
                positiveCallback,
                negativeCallback
            )
            UpdateState.NOTHING -> return

        }
    }

    override fun showWeakUpdateDialog(
        fragmentManager: FragmentManager,
        dialogParams: DialogParams,
        positiveCallback: () -> Unit,
        negativeCallback: () -> Unit
    ) {
        createDialog(fragmentManager, dialogParams, positiveCallback, negativeCallback)
            .show()
    }


    override fun showStrongUpdateDialog(
        fragmentManager: FragmentManager,
        dialogParams: DialogParams,
        positiveCallback: () -> Unit,
        negativeCallback: () -> Unit
    ) {
        createDialog(fragmentManager, dialogParams, positiveCallback, negativeCallback)
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