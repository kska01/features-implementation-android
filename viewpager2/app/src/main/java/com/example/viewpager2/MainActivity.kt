package com.example.viewpager2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpager2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager.apply {
            adapter = ViewPagerAdapter(getFoodList())
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

        val springDotsIndicator = binding.springDotsIndicator
        springDotsIndicator.attachTo(binding.pager)
    }

    private fun getFoodList(): ArrayList<Int> {
        return arrayListOf<Int>(R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4)
    }
}