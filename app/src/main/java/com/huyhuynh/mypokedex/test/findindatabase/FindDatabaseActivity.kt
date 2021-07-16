package com.huyhuynh.mypokedex.test.findindatabase

import AccountZ
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.huyhuynh.mypokedex.R
import com.huyhuynh.mypokedex.data.model.Account
import com.huyhuynh.mypokedex.database.MyDataBase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class FindDatabaseActivity : AppCompatActivity() {
    lateinit var edtFind: EditText
    lateinit var btnFind: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_database)
        createDB()
        edtFind = findViewById(R.id.edtFind)
        btnFind = findViewById(R.id.btnFind)

        btnFind.setOnClickListener {
            val text  = edtFind.text

        }
    }


    private fun createDB(){
        val myDB = MyDataBase.getDatabase(this)
//        GlobalScope.launch {
//            for (i in 0..1000000){
//                val rd = Random(0).nextInt(10)
//                val acc = AccountZ(null,i.toString(),"Huynh$rd Bao Huy $i","xxxx","xxxxx")
//                myDB.acountDao().insert(acc)
//            }
//            Log.d("localDBTest","Done Insert DB!!!!========")
//        }
    }

    private fun searchDB(name: String){
        val listTest = mu
    }
}