package com.huyhuynh.mypokedex.test.qrcode

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.huyhuynh.mypokedex.R
import com.huyhuynh.mypokedex.data.model.Account
import com.huyhuynh.mypokedex.data.model.MutilTestCase
import com.huyhuynh.mypokedex.screen.utils.JsonTransformer
import com.huyhuynh.mypokedex.screen.utils.Utils


class QRActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qractivity)
        //
        checkAndRequestPermissions()
        //
        init()

    }

    private fun init() {
        testJson()
    }



    //Kiểm tra và xin quyền trên android 6.0 trở lên
    private fun checkAndRequestPermissions() {
        val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                listPermissionsNeeded.add(permission)
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(), 199)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,permissions: Array<out String>,grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 199 && grantResults.isNotEmpty() && (grantResults[0] + grantResults[1]) == PackageManager.PERMISSION_GRANTED) {

        }
    }

    private fun testJson() {
        Utils.log("testJson","============START JSON============")

        val account1 = Account("1","Name 1","URL 1","No description")
        val account2 = Account("2","Name 2","URL 2","No description")
        val account3 = Account("3","Name 3","URL 3","No description")
        val account4 = Account("4","Name 4","URL 4","No description")
        val account5 = Account("5","Name 5","URL 5","No description")
        val account6 = Account("6","Name 6","URL 6","No description")
        val listAc: ArrayList<Account> = ArrayList()
        listAc.add(account1)
        listAc.add(account2)
        listAc.add(account3)
        listAc.add(account4)
        listAc.add(account5)
        listAc.add(account6)

        val stringJsonAcount = JsonTransformer.getInstance<Account>().objectToJsonString(account1)
        Utils.log("testJson","stringJsonAcount: $stringJsonAcount")
        val stringJsonArray = JsonTransformer.getInstance<Account>().arrayToJsonString(listAc)
        Utils.log("testJson","stringJsonArray: $stringJsonArray")
        //Convert jsonObject
        val jsonObject1 = JsonTransformer.getInstance<Account>().jsonStringToJSONObject(stringJsonAcount)
        val jsonObject2 = JsonTransformer.getInstance<Account>().jsonStringToJsonObject(stringJsonAcount)
        val jsonObject3 = JsonTransformer.getInstance<Account>().jsonStringToObject<Account>(stringJsonAcount)
        Utils.log("testJson","jsonObject1: ${jsonObject1}")
        Utils.log("testJson","jsonObject2: ${jsonObject2}")
        Utils.log("testJson","jsonObject3: ${jsonObject3?.name}")
        //Convert jsonArray
        val jsonArray2:Array<Account> = Gson().fromJson(stringJsonArray, Array<Account>::class.java)
        for (i in jsonArray2.indices){
            Utils.log("testJson","Json Array2: ${jsonArray2[i].name}")
        }
        val jsonArray1:Array<Account> = JsonTransformer.getInstance<Array<Account>>().jsonStringToArray(stringJsonArray)
        for (i in jsonArray1.indices){
            Utils.log("testJson","Json Array1: ${jsonArray1[i].name}")
        }
        val mutal = mutableListOf<Account>()
        mutal.addAll(jsonArray1)
        for (i in mutal.indices){
            Utils.log("testJson","Json Array3: ${mutal[i].name}")
        }
        Utils.log("testJson","--------------------------------------------------------------------")
        val mutil = MutilTestCase("Multi",account1,listAc,"X2222222")
        val mutlJsonStr = JsonTransformer.getInstance<MutilTestCase>().objectToJsonString(mutil)
        Utils.log("testJson","mutlJsonStr: $mutlJsonStr")
        val obj1: Account? = JsonTransformer.getInstance<Account>().getObjectFromJsonString(mutlJsonStr,"account")
        Utils.log("testJson","obj1: ${obj1?.name}")
        val listMulti: Array<Account> ?= JsonTransformer.getInstance<Array<Account>>().getObjectFromJsonString(mutlJsonStr,"listAccount")
        for (i in listMulti?.indices!!){
            Utils.log("testJson","Json listMulti: ${listMulti[i].name}")
        }
        val strDes: String ?= JsonTransformer.getInstance<String>().getObjectFromJsonString(mutlJsonStr,"xdescription")
        Utils.log("testJson","strDes: $strDes")

        Utils.log("testJson","============END JSON============")
    }
}