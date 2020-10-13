package com.pikolive.module.share

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.internet.boy.androidbase.inflate
import com.pikolive.module.R

/**
 * Creator: ED
 * Date: 2020/10/12 2:05 PM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class ShareAdapter : RecyclerView.Adapter<ShareAdapter.ViewHolder>() {

    private val mData = mutableListOf<ShareItem>()
    private var mClickListener: ClickListener? = null


    fun setData(shareItems: List<ShareItem>) {
        mData.clear()
        mData.addAll(shareItems)
    }

    fun setOnItemClick(clickListener: ClickListener) {
        this.mClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_share))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shareItem = mData[position]
        holder.bind(shareItem)

        holder.itemView.setOnClickListener {
            mClickListener?.onItemClick(shareItem)
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(shareItem: ShareItem) {

            itemView.findViewById<ImageView>(R.id.ivShare)
                .setImageResource(shareItem.iconDrawableRes)

            itemView.findViewById<TextView>(R.id.tvShareTag).text = shareItem.text
        }
    }

    interface ClickListener {
        fun onItemClick(item: ShareItem)
    }
}
