package com.example.viewpager2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(var fruitList: ArrayList<Int>) :
    RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {

    inner class PagerViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val food: ImageView = view.findViewById(R.id.imageView_food)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_list_item, parent, false)
        return PagerViewHolder(view)
    }

    override fun getItemCount(): Int = fruitList.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.food.setImageResource(fruitList[position])
    }


}