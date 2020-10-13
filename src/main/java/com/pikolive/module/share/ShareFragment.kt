package com.pikolive.module.share

import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.internet.boy.androidbase.common.CustomGridLayoutManager
import com.internet.boy.androidbase.common.GridSpacingItemDecoration
import com.internet.boy.androidbase.kutils.dip2px
import com.pikolive.module.R

/**
 * Creator: ED
 * Date: 2020/10/12 11:19 AM
 * Mail: salahayo3192@gmail.com
 *
 * **/

class ShareFragment : DialogFragment(), ShareAdapter.ClickListener {
    companion object {
        const val BUNDLE_SHARE_TITLE = "BUNDLE_SHARE_TITLE"
        const val BUNDLE_SHARE_CONTENT = "BUNDLE_SHARE_CONTENT"
    }

    override fun onStart() {
        super.onStart()

        val point = Point()
        val windowManager = requireActivity().windowManager
        windowManager.defaultDisplay.getSize(point)


        val dialog = requireDialog()
        dialog.window?.let {
            val params = it.attributes


            params.width = (point.x * 1).toInt()
            params.height = (point.y * 1).toInt()

            params.dimAmount = 0.0f

            isCancelable = true

            it.attributes = params
        }

        dialog.setCanceledOnTouchOutside(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireDialog().window?.setBackgroundDrawable(ColorDrawable(Color.BLACK).apply {
            alpha = 160

        })

        return inflater.inflate(R.layout.fragment_share, container, false)
    }

    override fun onViewCreated(rootView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(rootView, savedInstanceState)
        rootView.setOnClickListener {
            dismiss()
        }

        val tvShareTitle = rootView.findViewById<TextView>(R.id.tvShareTitle)
        val shareTitle = arguments?.getString(BUNDLE_SHARE_TITLE, "")
        if (shareTitle == null || shareTitle.isEmpty()) {
            tvShareTitle.visibility = View.GONE
        } else {
            tvShareTitle.text = shareTitle
        }

        val rvShare = rootView.findViewById<RecyclerView>(R.id.rvShare)
        rvShare.layoutManager = CustomGridLayoutManager(requireContext(), 3)
        rvShare.addItemDecoration(GridSpacingItemDecoration(3, rootView.context.dip2px(14), true))
        rvShare.adapter = ShareAdapter().apply {
            this.setData(createShareItems())
            this.setOnItemClick(this@ShareFragment)
        }
    }

    override fun onItemClick(item: ShareItem) {
        val shareContent = arguments?.getString(BUNDLE_SHARE_CONTENT, "") ?: ""

        ShareManager.getInstance()
            .shareContent(requireContext(), item.type, shareContent)
    }

    private fun createShareItems(): List<ShareItem> {
        return listOf(
            ShareItem(
                R.drawable.ic_share_line, "LINE", SharePlatformType.LINE
            ),
            ShareItem(
                R.drawable.ic_share_instagram, "Instagram", SharePlatformType.INSTAGRAM
            ),
            ShareItem(
                R.drawable.ic_share_facebook_messenger, "Messenger", SharePlatformType.MESSENGER
            ),
            ShareItem(
                R.drawable.ic_share_telegram, "Telegram", SharePlatformType.TELEGRAM
            ),
            ShareItem(
                R.drawable.ic_share_copylink, "複製鏈結", SharePlatformType.COPY_LINK
            ),
            ShareItem(
                R.drawable.ic_share_other, "顯示更多", SharePlatformType.OTHER
            )
        )
    }


}