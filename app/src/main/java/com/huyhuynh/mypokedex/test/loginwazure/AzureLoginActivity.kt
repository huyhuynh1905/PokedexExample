package com.huyhuynh.mypokedex.test.loginwazure

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebResourceRequest
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
    val path3 = "https://afc.unit.vn/login"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_azure_login)
        btnLoginWAzure.setOnClickListener {
            loadUrlGoogle()
        }
    }


    var flag = false
    private fun loadUrlGoogle(){
        wv_viewGoogle.apply {
            canGoBack()
            loadUrl(path3)
            webViewClient = object : WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    if (url?.contains("") == true) {
                        flag = true
                    }
                }

                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    return super.shouldOverrideUrlLoading(view, request)

                }
            }
        }
    }
}