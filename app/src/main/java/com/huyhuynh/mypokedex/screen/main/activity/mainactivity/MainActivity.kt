package com.huyhuynh.mypokedex.screen.main.activity.mainactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.huyhuynh.mypokedex.R
import com.huyhuynh.mypokedex.screen.main.activity.login.LoginActivity
import com.huyhuynh.mypokedex.screen.main.fragment.pokedex.PokedexListFragment
import com.huyhuynh.mypokedex.screen.main.fragment.pokedex.PokedexListFragmentArgs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var emailData = intent.getStringExtra("email")
        findNavController(R.id.nav_host_fragment)
            .setGraph(
                R.navigation.nav_graph,
                PokedexListFragmentArgs(emailData).toBundle()
            )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        var intent1 =  Intent(this, LoginActivity::class.java)
        startActivity(intent1)
        finish()
    }
}