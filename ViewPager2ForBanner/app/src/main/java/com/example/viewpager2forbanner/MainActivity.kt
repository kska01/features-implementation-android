package com.example.viewpager2forbanner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
                add(DataPage(android.R.color.holo_orange_light, "2 Page"))
                add(DataPage(android.R.color.holo_green_light, "3 Page"))
                add(DataPage(android.R.color.holo_blue_bright, "4 Page"))
                add(DataPage(android.R.color.holo_purple, "5 Page"))
                add(DataPage(android.R.color.darker_gray, "6 Page"))
            }
        }

        binding.viewPager.adapter = ViewPagerAdapter(list)
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        // banner 의 숫자 설정
        binding.txtCurrentBanner.text = getString(R.string.viewpager2_banner, 1, list.size)
        // banner 를 드래그해서 넘겼을 때 포지션 값을 변경해주는 callBack 함수 등록
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.txtCurrentBanner.text =
                    getString(R.string.viewpager2_banner, position + 1, list.size)
            }
        })
    }
}