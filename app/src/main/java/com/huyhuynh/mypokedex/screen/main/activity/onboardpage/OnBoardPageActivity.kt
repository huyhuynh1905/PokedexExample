package com.huyhuynh.mypokedex.screen.main.activity.onboardpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.huyhuynh.mypokedex.R
import com.huyhuynh.mypokedex.data.adapter.OnboardViewPagerAdapter
import com.huyhuynh.mypokedex.screen.main.activity.login.LoginActivity
import me.relex.circleindicator.CircleIndicator

class OnBoardPageActivity : AppCompatActivity() {
    lateinit var tvSkip: TextView
    lateinit var tvNext: TextView
    lateinit var viewPager: ViewPager
    lateinit var circle: CircleIndicator
    lateinit var bottomLayout: RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board_page)

        init()
        val viewPagerAdapter = OnboardViewPagerAdapter(supportFragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        viewPager.adapter = viewPagerAdapter
        circle.setViewPager(viewPager)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
                //
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                //
            }

            override fun onPageSelected(position: Int) {
                //
                if (position==2){
                    tvSkip.visibility = View.GONE
                    bottomLayout.visibility = View.GONE
                } else{
                    tvSkip.visibility = View.VISIBLE
                    bottomLayout.visibility = View.VISIBLE
                }
            }

        })
        //
        tvSkip.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            overridePendingTransition(R.anim.slide_enter,R.anim.slide_exit)
            finish()
        }

        tvNext.setOnClickListener {
            if (viewPager.currentItem < 2){
                viewPager.currentItem = viewPager.currentItem + 1
            }
        }

    }

    private fun init() {
        tvSkip = findViewById(R.id.tvSkip)
        tvNext = findViewById(R.id.tvNextOnboard)
        viewPager = findViewById(R.id.viewPagerOnboard)
        circle = findViewById(R.id.circle_onboard)
        bottomLayout = findViewById(R.id.layoutBottom)
    }
}