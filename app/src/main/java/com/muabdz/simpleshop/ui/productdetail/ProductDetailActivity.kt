package com.muabdz.simpleshop.ui.productdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.muabdz.simpleshop.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}