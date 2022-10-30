package com.example.viewpager2forbanner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpager2forbanner.data.DataPage

class ViewPagerAdapter(private val listData: ArrayList<DataPage>) : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {

    class PagerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: AppCompatTextView = view.findViewById(R.id.tv_title)
        val background: RelativeLayout = view.findViewById(R.id.rl_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_viewpager, parent, false)
        return PagerViewHolder(layout)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.textView.text = listData[position].title
        holder.background.setBackgroundResource(listData[position].color)
    }

    override fun getItemCount(): Int = listData.size
}