package com.muabdz.simpleshop.ui.productlist.purchasehistory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.muabdz.simpleshop.databinding.ActivityPurchaseHistoryBinding

class PurchaseHistoryActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityPurchaseHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPurchaseHistoryBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}