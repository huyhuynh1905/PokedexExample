package com.huyhuynh.mypokedex.screen.utils

import android.content.Context
import android.content.SharedPreferences
import demo.com.weatherapp.MainApplication

class SharedPreferencesUtils private constructor() {
    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = MainApplication.getContextInstance().getSharedPreferences(SHAREDPREFERENCES,
            Context.MODE_PRIVATE)
    }

    fun saveDataOnboarding(data: Boolean){
        var editor = sharedPreferences.edit()
        editor.putBoolean("firtload",data)
        editor.apply()
    }

    fun getDataOnboarding():Boolean{
        var result: Boolean = sharedPreferences.getBoolean("firtload",false)
        return result
    }


    companion object {
        private val SHAREDPREFERENCES = "save_onboard"
        private val instance: SharedPreferencesUtils?=null
        fun getInstance():SharedPreferencesUtils{
            if (instance==null){
                return SharedPreferencesUtils()
            }
            return instance
        }
    }

}