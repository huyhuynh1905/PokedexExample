package demo.com.weatherapp.screen.base.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), View.OnClickListener {

    abstract val layoutResource: Int

    abstract fun initVariable(savedInstanceState: Bundle?)

    abstract fun initData(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        initVariable(savedInstanceState)
        initData(savedInstanceState)
    }
}