package com.example.viewpager2forbanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpager2forbanner.data.DataPage
import com.example.viewpager2forbanner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list: ArrayList<DataPage> = ArrayList<DataPage>().let {
            it.apply {
                add(DataPage(android.R.color.holo_red_light, "1 Page"))
                add(DataPage(android.R.color.holo_orange_dark, "2 Page"))
                add(DataPage(android.R.color.holo_green_dark, "3 Page"))
                add(DataPage(android.R.color.holo_blue_bright, "4 Page"))
                add(DataPage(android.R.color.holo_purple, "5 Page"))
                add(DataPage(android.R.color.darker_gray, "6 Page"))
            }
        }

        binding.viewPager.adapter = ViewPagerAdapter(list)
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }
}