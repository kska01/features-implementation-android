package com.example.slidingpanelayout.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.slidingpanelayout.model.Colors
import com.example.slidingpanelayout.R

class ListPaneAdapter(
    private val context: Context,
    private val data: MutableList<Colors>
) :RecyclerView.Adapter<ListPaneAdapter.ViewHolder>() {

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val imageButton: ImageButton = view.findViewById(R.id.color_button)
        val name: TextView = view.findViewById(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_layout, parent, false)

        return ViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.imageButton.setImageResource(item.imageResourceId)
        holder.name.text = context.getString(item.nameId)
    }

    override fun getItemCount(): Int = data.size
}