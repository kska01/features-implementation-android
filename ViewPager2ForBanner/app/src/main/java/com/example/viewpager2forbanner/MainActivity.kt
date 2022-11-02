package com.example.viewpager2forbanner

import android.animation.Animator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpager2forbanner.data.DataPage
import com.example.viewpager2forbanner.databinding.ActivityMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var bannerPosition = 0

    // Coroutine
    lateinit var job: Job

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
        // banner 의 숫자 설정. 아래의 registerOnPageChangeCallback 함수와 중복 되므로 주석 처리함.
//        binding.txtCurrentBanner.text = getString(R.string.viewpager2_banner, 1, list.size)
        // banner 를 드래그해서 넘겼을 때 포지션 값을 변경해주는 callBack 함수 등록
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            // 사용자가 스크롤 했을 때 하단에 표시되는 숫자의 변경
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bannerPosition = position
                binding.txtCurrentBanner.text =
                    getString(R.string.viewpager2_banner, (bannerPosition % list.size) + 1, list.size)
            }

            // Scroll 의 상태 정보를 이용하여 자동 스크롤을 구현한다.
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                when (state) {
                    ViewPager2.SCROLL_STATE_IDLE -> {
                        if (!job.isActive) scrollJobCreate()
                    }
                    ViewPager2.SCROLL_STATE_DRAGGING -> job.cancel()
                    ViewPager2.SCROLL_STATE_SETTLING -> {}
                }
            }
        })

        // viewpager 스크롤 시작시 좌-우로 모두 가능하게 만들기. 즉 1 페이지에서 왼쪽으로 드래그시 6 페이지가
        // 나오고 오른쪽으로 드래그시 2 페이지가 나오도록 하기.
        bannerPosition = Int.MAX_VALUE / 2 - ceil(list.size.toDouble() / 2).toInt()
        binding.viewPager.setCurrentItem(bannerPosition, false)

    }

    // 자동 스크롤의 간격이 아닌 스크롤 되는 애니메이션의 속도 조절. ViewPager2 자체에 Duration 기능이
    // 없어 확장 함수를 만든다.
    fun ViewPager2.setCurrentItemWithDuration(
        item: Int,
        duration: Long,
        interpolator: TimeInterpolator = AccelerateDecelerateInterpolator(),
        pagePxWidth: Int = width,
        pagePxHeight: Int = height
    ) {
        val pxToDrag: Int = if (orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
            pagePxWidth * (item - currentItem)
        } else {
            pagePxHeight * (item - currentItem)
        }
        val animator = ValueAnimator.ofInt(0, pxToDrag)
        var previousValue = 0

        animator.addUpdateListener { valueAnimator ->
            val currentValue = valueAnimator.animatedValue as Int
            val currentPxToDrag = (currentValue - previousValue).toFloat()
            fakeDragBy(-currentPxToDrag)
            previousValue = currentValue
        }

        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) { beginFakeDrag() }

            override fun onAnimationEnd(p0: Animator?) { endFakeDrag() }

            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationRepeat(p0: Animator?) {}
        })

        animator.interpolator = interpolator
        animator.duration = duration
        animator.start()
    }

    // Coroutine 과 delay 메소드를 활용하여 지정한 시간마다 배너의 위치를 갱신한다.
    private fun scrollJobCreate() {
        job = lifecycleScope.launchWhenResumed {
            delay(3000)
            binding.viewPager.setCurrentItemWithDuration(++bannerPosition, 1000)
        }
    }

    // lifecycle 이 onResume 일 때 자동 스크롤을 실행시킨다
    override fun onResume() {
        super.onResume()
        scrollJobCreate()
    }

    // activity 에 focus 가 제거 되었을 시에 Coroutine 의 job 을 취소시켜 자동 스크롤을 중지시킨다.
    override fun onPause() {
        super.onPause()
        job.cancel()
    }
}