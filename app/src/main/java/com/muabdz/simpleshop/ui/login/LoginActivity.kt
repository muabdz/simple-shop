package com.muabdz.simpleshop.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.muabdz.simpleshop.databinding.ActivityLoginBinding
import com.muabdz.simpleshop.ui.home.HomeActivity
import com.orhanobut.hawk.Hawk

class LoginActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.btnSignIn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        Hawk.init(this).build()
    }
}