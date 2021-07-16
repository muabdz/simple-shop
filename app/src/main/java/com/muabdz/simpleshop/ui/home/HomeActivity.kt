package com.muabdz.simpleshop.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.muabdz.simpleshop.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


    }
}