package com.example.slidingpanelayout.data

import com.example.slidingpanelayout.R
import com.example.slidingpanelayout.model.Colors

class ColorData {
    fun loadColorData(): MutableList<Colors> {
        return mutableListOf<Colors>(
            Colors(
                R.color.red,
                R.string.red
            ),
            Colors(
                R.color.orange,
                R.string.orange
            ),
            Colors(
                R.color.yellow,
                R.string.yellow
            ),
            Colors(
                R.color.green,
                R.string.green
            ),
            Colors(
                R.color.blue,
                R.string.blue
            ),
            Colors(
                R.color.indigo,
                R.string.indigo
            ),
            Colors(
                R.color.purple,
                R.string.purple
            )
        )
    }
}