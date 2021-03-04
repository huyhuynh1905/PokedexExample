package com.huyhuynh.mypokedex.screen.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.util.Log


class CheckNetwork {
    var context: Context? = null

    constructor(context: Context) {
        this.context = context
    }

    // Network Check
    fun registerNetworkCallback() {
        try {
            val connectivityManager =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager.registerDefaultNetworkCallback(object : NetworkCallback() {
                override fun onAvailable(network: Network) {
                    Log.d("Internet","Có mạng!")
                    isNetworkConnected = true
                }

                override fun onLost(network: Network) {
                    Log.d("Internet","Không có mạng!")
                    isNetworkConnected = false
                }
            }
            )
        } catch (e: Exception) {
            Log.d("Internet","Error: ${e.printStackTrace()}")
            isNetworkConnected = false
        }
    }

    companion object{
        private var isNetworkConnected: Boolean = false
        fun isConnectedNetwork():Boolean = isNetworkConnected
    }
}