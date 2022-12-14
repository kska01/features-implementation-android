package com.example.viewpager2withtap

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.viewpager2withtap.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tabTitleArray = arrayOf(
        "Tab1",
        "Tab2",
        "Tab3"
    )
    private val tabIconArray = arrayOf(
        R.drawable.ic_sunny,
        R.drawable.ic_star,
        R.drawable.ic_florist
    )

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        // TabLayout 과 Viewpager2 를 연동하기
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            // Tab의 이름 설정
            tab.text = tabTitleArray[position]
            // Tab에 icon 넣기
            tab.icon = getDrawable(tabIconArray[position])
        }.attach()
    }
}