package com.example.viewpager2forbanner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpager2forbanner.data.DataPage

class ViewPagerAdapter(private val listData: ArrayList<DataPage>) :
    RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {

    class PagerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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
        val item = listData[position % listData.size]
        holder.textView.text = item.title
        holder.background.setBackgroundResource(item.color)
    }

    // 무한 스크롤 효과를 내기 위해 아이템의 숫자를 최대한 크게 설정해 준다.
    override fun getItemCount(): Int = Int.MAX_VALUE
}