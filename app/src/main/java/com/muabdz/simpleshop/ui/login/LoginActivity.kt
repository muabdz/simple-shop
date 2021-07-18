package com.muabdz.simpleshop.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.muabdz.simpleshop.R
import com.muabdz.simpleshop.databinding.ActivityLoginBinding
import com.muabdz.simpleshop.ui.home.HomeActivity
import com.orhanobut.hawk.Hawk

class LoginActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityLoginBinding
    private lateinit var callbackManager: CallbackManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        Hawk.init(this).build()

        viewBinding.btnSignIn.setOnClickListener {
            navigateToHome()
        }
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

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
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