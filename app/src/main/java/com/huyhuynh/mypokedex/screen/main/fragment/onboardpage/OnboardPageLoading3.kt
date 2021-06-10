package com.huyhuynh.mypokedex.screen.main.fragment.onboardpage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.huyhuynh.mypokedex.R
import com.huyhuynh.mypokedex.screen.main.activity.login.LoginActivity

class OnboardPageLoading3 : Fragment() {

    var btnGetting : Button? =null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_onboard_page_loading3, container, false)
        init(view)
        return view
    }

    private fun init(view: View?) {
        btnGetting = view?.findViewById(R.id.btn_getting)

        btnGetting?.setOnClickListener {
            var intent = Intent(activity,LoginActivity::class.java)
            activity?.let {
                it.startActivity(intent)
                it.overridePendingTransition(R.anim.slide_enter,R.anim.slide_exit)
                it.finish()
            }
        }
    }

}