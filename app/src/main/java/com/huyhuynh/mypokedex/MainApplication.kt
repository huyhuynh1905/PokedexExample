package demo.com.weatherapp

import android.app.Application
import com.google.gson.Gson

class MainApplication : Application() {

    var gSon: Gson? = null

    companion object {
        private var mContext: MainApplication? = null
        fun getContextInstance(): MainApplication {
            return mContext!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
        gSon = Gson()
    }
}
