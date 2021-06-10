package com.huyhuynh.mypokedex.screen.main.activity.splash

import android.app.ActivityManager
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.huyhuynh.mypokedex.R
import com.huyhuynh.mypokedex.screen.main.activity.login.LoginActivity
import com.huyhuynh.mypokedex.screen.main.activity.onboardpage.OnBoardPageActivity
import com.huyhuynh.mypokedex.screen.utils.SharedPreferencesUtils


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var imgPikachu: ImageView = findViewById(R.id.imgPikachu)
        val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.anim_splash)
        imgPikachu.startAnimation(animation)
        handlerLoadLoginActivity()
        Log.d("checkRam", "RAM Total: ${checkRam()}")
    }

    fun handlerLoadLoginActivity(){
        Handler().postDelayed({
            if (SharedPreferencesUtils.getInstance().getDataOnboarding()) {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit)
                finish()
            } else {
                SharedPreferencesUtils.getInstance().saveDataOnboarding(true)
                val intent = Intent(this@SplashActivity, OnBoardPageActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit)
                finish()
            }

        }, 2000)
    }

    fun checkRam(): Long{
        val mi = ActivityManager.MemoryInfo()
        val activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        activityManager.getMemoryInfo(mi)
        return mi.availMem / 1048576L
    }
}