package com.huyhuynh.mypokedex.test.loginwazure

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import com.huyhuynh.mypokedex.R
import com.huyhuynh.mypokedex.screen.utils.Utils
import kotlinx.android.synthetic.main.activity_azure_login.*
import okhttp3.internal.Util
import org.json.JSONObject

class AzureLoginActivity : AppCompatActivity() {
    val path1 = "https://accounts.google.com"
    val path2 = "https://login.microsoftonline.com"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_azure_login)
        btnLoginWAzure.setOnClickListener {
            loadUrlGoogle()
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun loadUrlGoogle(){
        wv_viewGoogle.apply {
            settings.javaScriptEnabled = true
            canGoBack()
            "Mozilla/5.0 AppleWebKit/535.19 Chrome/56.0.0 Mobile Safari/535.19".also { settings.userAgentString = it }
            loadUrl(path1)
            webViewClient = object : WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    view?.let {
                        val cookie = CookieManager.getInstance().getCookie(url)
                        Utils.log("loadUrlGoogle","Load by cookie: $cookie")
                        it.evaluateJavascript("(function() { return JSON.stringify(localStorage); })();") { s ->
                            if (s != "\"{}\"") {
                                val jsonAsStr = s.substring(1, s.length - 1).replace("\\", "")
                                Utils.log("loadUrlGoogle","jsonAsStr: $jsonAsStr")
//                                    val obj = JSONObject(jsonAsStr)
//                                    val token = obj.getString("token")
//                                    Utils.log("loadUrlGoogle","Load by token: $token")
                            }
                        }
                    }
                }
            }
        }
    }
}