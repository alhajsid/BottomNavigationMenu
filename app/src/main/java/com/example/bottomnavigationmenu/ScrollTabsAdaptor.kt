package com.example.bottomnavigationmenu

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.ImageView

import androidx.recyclerview.widget.RecyclerView


internal class ScrollTabsAdaptor(
    private val tabModals: ArrayList<TabModal>,
    private val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<ScrollTabsAdaptor.PagerViewHolder>() {

    interface OnItemClickListener {
        fun onViewClick(tabModal: TabModal?)
    }

    internal inner class PagerViewHolder(view: View, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(view), View.OnClickListener {
        val imageview: ImageView = itemView.findViewById(R.id.tab_image)
        val mListener: OnItemClickListener = listener

        override fun onClick(v: View?) {
            mListener.onViewClick(tabModals[adapterPosition])
        }

        init {
            imageview.setOnClickListener(this)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PagerViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.tab_item, parent, false)
        return PagerViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(
        holder: PagerViewHolder,
        position: Int
    ) {
        val tabModel = tabModals[position]
        holder.imageview.setBackground(tabModel.drawable)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return tabModals.size
    }

}