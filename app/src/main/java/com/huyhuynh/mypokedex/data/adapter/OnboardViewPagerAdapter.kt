package com.huyhuynh.mypokedex.data.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.huyhuynh.mypokedex.screen.main.fragment.onboardpage.OnboardPageLoading1
import com.huyhuynh.mypokedex.screen.main.fragment.onboardpage.OnboardPageLoading2
import com.huyhuynh.mypokedex.screen.main.fragment.onboardpage.OnboardPageLoading3

class OnboardViewPagerAdapter : FragmentStatePagerAdapter {

    constructor(fm: FragmentManager, behavior: Int) : super(fm, behavior){

    }


    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return OnboardPageLoading1()
            1 -> return OnboardPageLoading2()
            2 -> return OnboardPageLoading3()
            else -> return OnboardPageLoading1()
        }
    }

    override fun getCount(): Int {
        return 3
    }
}