package com.huyhuynh.mypokedex.screen.utils

import android.util.Log
import com.huyhuynh.mypokedex.BuildConfig

object Utils {
    fun log(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, message)
        }
    }
}