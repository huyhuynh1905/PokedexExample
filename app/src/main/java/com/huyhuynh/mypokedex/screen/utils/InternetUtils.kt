package com.huyhuynh.mypokedex.screen.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object InternetUtils {
    @Suppress("DEPRECATION")
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}