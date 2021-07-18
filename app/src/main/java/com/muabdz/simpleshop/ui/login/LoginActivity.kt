package com.muabdz.simpleshop.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.muabdz.simpleshop.R
import com.muabdz.simpleshop.databinding.ActivityLoginBinding
import com.muabdz.simpleshop.ui.home.HomeActivity
import com.orhanobut.hawk.Hawk


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewBinding: ActivityLoginBinding
    private lateinit var callbackManager: CallbackManager
    private lateinit var googleSignInClient: GoogleSignInClient

    companion object {
        const val RQ_GOOGLE_SIGN_IN = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        Hawk.init(this).build()

        viewBinding.btnSignIn.setOnClickListener(this)
        viewBinding.btnLoginGoogle.setOnClickListener(this)
        callbackManager = CallbackManager.Factory.create()
        viewBinding.btnLoginFb.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                navigateToHome()
            }

            override fun onCancel() {
                showLoginError()
            }

            override fun onError(error: FacebookException?) {
                showLoginError()
            }
        })
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        viewBinding.btnLoginGoogle.setSize(SignInButton.SIZE_STANDARD)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_sign_in -> navigateToHome()
            R.id.btn_login_google -> googleSignIn()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RQ_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            googleSignInResult(task)
        }
    }

    private fun googleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            completedTask.getResult(ApiException::class.java)
            navigateToHome()
        } catch (e: ApiException) {
            showLoginError()
        }
    }

    private fun googleSignIn() {
        val intent = googleSignInClient.signInIntent
        startActivityForResult(intent, RQ_GOOGLE_SIGN_IN)
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showLoginError() {
        Toast.makeText(this, getString(R.string.wording_login_failed), Toast.LENGTH_SHORT).show()
    }

}