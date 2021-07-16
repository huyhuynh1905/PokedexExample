package com.huyhuynh.mypokedex.test.loginwazure

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import com.huyhuynh.mypokedex.R
import com.huyhuynh.mypokedex.screen.main.activity.mainactivity.MainActivity
import com.huyhuynh.mypokedex.screen.utils.Utils
import com.microsoft.graph.concurrency.ICallback
import com.microsoft.graph.core.ClientException
import com.microsoft.graph.models.extensions.*
import com.microsoft.graph.requests.extensions.GraphServiceClient
import com.microsoft.identity.client.*
import com.microsoft.identity.client.PublicClientApplication
import com.microsoft.identity.client.exception.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class AzureLoginActivity : AppCompatActivity() {

    private val SCOPES = arrayOf("Files.Read")

    /* Azure AD v2 Configs */
    val AUTHORITY = "https://login.microsoftonline.com/common"
    private var mSingleAccountApp: ISingleAccountPublicClientApplication? = null

    private val TAG = MainActivity::class.java.simpleName

    /* UI & Debugging Variables */
    var signInButton: Button? = null
    var signOutButton: Button? = null
    var callGraphApiInteractiveButton: Button? = null
    var callGraphApiSilentButton: Button? = null
    var logTextView: TextView? = null
    var currentUserTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_azure_login)

        initializeUI()

        try {
            val info = packageManager.getPackageInfo(
                "com.huyhuynh.mypokedex",
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d(
                    "KeyHash", "KeyHash:" + Base64.encodeToString(
                        md.digest(),
                        Base64.DEFAULT
                    )
                )
            }
        } catch (e: PackageManager.NameNotFoundException) {
            Utils.log("KeyHash","NameNotFoundException: ${e.message}")
        } catch (e: NoSuchAlgorithmException) {
            Utils.log("KeyHash","NoSuchAlgorithmException: ${e.message}")
        }

        PublicClientApplication.createSingleAccountPublicClientApplication(
            application,
            R.raw.auth_config_single_account, object :
                IPublicClientApplication.ISingleAccountApplicationCreatedListener {
                override fun onCreated(application: ISingleAccountPublicClientApplication) {
                    mSingleAccountApp = application
                    loadAccount()
                }

                override fun onError(exception: MsalException?) {
                    exception?.let { displayError(it) }
                }
            })
    }
    private fun performOperationOnSignOut() {
        val signOutText = "Signed Out."
        currentUserTextView?.text = ""
        Toast.makeText(applicationContext, signOutText, Toast.LENGTH_SHORT)
            .show()
    }

    private fun displayGraphResult(@NonNull graphResponse: JsonObject) {
        logTextView?.text = graphResponse.toString()
    }

    private fun displayError(@NonNull exception: Exception) {
        logTextView?.text = exception.toString()
    }
    private fun updateUI(@Nullable account: IAccount?) {
        if (account != null) {
            signInButton?.isEnabled = false
            signOutButton?.isEnabled = true
            callGraphApiInteractiveButton?.isEnabled = true
            callGraphApiSilentButton?.isEnabled = true
            currentUserTextView?.text = account.username
        } else {
            signInButton?.isEnabled = true
            signOutButton?.isEnabled = false
            callGraphApiInteractiveButton?.isEnabled = false
            callGraphApiSilentButton?.isEnabled = false
            currentUserTextView?.text = ""
            logTextView?.text = ""
        }
    }

    private fun callGraphAPI(authenticationResult: IAuthenticationResult) {
        val accessToken: String = authenticationResult.getAccessToken()
        val graphClient: IGraphServiceClient = GraphServiceClient
            .builder()
            .authenticationProvider { request ->
                Utils.log("Authenticating","request: ${request.requestUrl}")
                Utils.log("Authenticating","Bearer: $accessToken")
                request.addHeader("Authorization", "Bearer $accessToken")
            }
            .buildClient()
        graphClient
            .me()
            .drive()
            .buildRequest()
            .get(object : ICallback<Drive?> {

                override fun failure(ex: ClientException?) {
                    if (ex != null) {
                        displayError(ex)
                    }
                }

                override fun success(result: Drive?) {
                    Log.d(TAG, "Found Drive " + result?.id)
                    result?.rawObject?.let { displayGraphResult(it) }
                }
            })
    }

    private fun getAuthSilentCallback(): SilentAuthenticationCallback {
        return object : SilentAuthenticationCallback {
            override fun onSuccess(authenticationResult: IAuthenticationResult?) {
                Log.d(TAG, "Successfully authenticated")
                if (authenticationResult != null) {
                    callGraphAPI(authenticationResult)
                }
            }

            override fun onError(exception: MsalException) {
                Log.d(TAG, "Authentication failed: $exception")
                displayError(exception)
            }
        }
    }

    private fun getAuthInteractiveCallback(): AuthenticationCallback {
        return object : AuthenticationCallback {
            override fun onSuccess(authenticationResult: IAuthenticationResult) {
                /* Successfully got a token, use it to call a protected resource - MSGraph */
                Log.d(TAG, "Successfully authenticated")
                /* Update UI */updateUI(authenticationResult.getAccount())
                /* call graph */callGraphAPI(authenticationResult)
            }

            override fun onError(exception: MsalException) {
                /* Failed to acquireToken */
                Log.d(TAG, "Authentication failed: " + exception.toString())
                displayError(exception)
            }

            override fun onCancel() {
                /* User canceled the authentication */
                Log.d(TAG, "User cancelled login.")
            }
        }
    }

    private fun initializeUI() {
        signInButton = findViewById(R.id.signIn)
        callGraphApiSilentButton = findViewById(R.id.callGraphSilent)
        callGraphApiInteractiveButton = findViewById(R.id.callGraphInteractive)
        signOutButton = findViewById(R.id.clearCache)
        logTextView = findViewById(R.id.txt_log)
        currentUserTextView = findViewById(R.id.current_user)

        //Sign in user
        signInButton?.setOnClickListener {
            Utils.log("initializeUI","signInButton: Click")

            if (mSingleAccountApp != null) {
                mSingleAccountApp?.signIn(
                    this@AzureLoginActivity,
                    "+84 91 899 77 48",
                    SCOPES,
                    getAuthInteractiveCallback()
                )
            }

        }

        //Sign out user
        signOutButton?.setOnClickListener {
            if (mSingleAccountApp != null) {
                mSingleAccountApp?.signOut(object : ISingleAccountPublicClientApplication.SignOutCallback {
                    override fun onSignOut() {
                        updateUI(null)
                        performOperationOnSignOut()
                    }

                    override fun onError(exception: MsalException) {
                        displayError(exception)
                    }
                })
            }

        }

        //Interactive
        callGraphApiInteractiveButton?.setOnClickListener {
            if (mSingleAccountApp != null) {
                mSingleAccountApp?.acquireToken(
                    this@AzureLoginActivity,
                    SCOPES,
                    getAuthInteractiveCallback()
                )
            }
        }

        //Silent
        callGraphApiSilentButton?.setOnClickListener{
            if (mSingleAccountApp != null) {
                mSingleAccountApp?.acquireTokenSilentAsync(
                    SCOPES,
                    AUTHORITY,
                    getAuthSilentCallback()
                )
            }
        }
    }

    private fun loadAccount() {
        if (mSingleAccountApp == null) {
            return
        }
        mSingleAccountApp?.getCurrentAccountAsync(object : ISingleAccountPublicClientApplication.CurrentAccountCallback {
            override fun onAccountLoaded(activeAccount: IAccount?) {
                // You can use the account data to update your UI or your app database.
                updateUI(activeAccount)
            }

            override fun onAccountChanged(priorAccount: IAccount?, currentAccount: IAccount?) {
                if (currentAccount == null) {
                    // Perform a cleanup task as the signed-in account changed.
                    performOperationOnSignOut()
                }
            }

            override fun onError(exception: MsalException) {
                displayError(exception)
            }
        })
    }
}