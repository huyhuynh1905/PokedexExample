package com.huyhuynh.mypokedex.screen.utils

import android.content.Context

object Utils {
    fun getImage(imageName: String, context: Context): Int {
        return context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }
}