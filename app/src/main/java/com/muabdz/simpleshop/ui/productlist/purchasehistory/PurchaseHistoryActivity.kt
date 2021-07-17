package com.muabdz.simpleshop.ui.productlist.purchasehistory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.muabdz.simpleshop.R
import com.muabdz.simpleshop.data.ProductEntity
import com.muabdz.simpleshop.databinding.ActivityPurchaseHistoryBinding
import com.muabdz.simpleshop.ui.productlist.ProductListCallback
import com.muabdz.simpleshop.ui.productdetail.ProductDetailActivity
import com.muabdz.simpleshop.ui.productlist.ProductListAdapter
import com.muabdz.simpleshop.ui.productlist.ProductListViewModel

class PurchaseHistoryActivity : AppCompatActivity(), ProductListCallback {
    private lateinit var viewBinding: ActivityPurchaseHistoryBinding
    private lateinit var viewModel: ProductListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPurchaseHistoryBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ProductListViewModel::class.java]

        viewBinding.rvProducts.visibility = View.INVISIBLE
        viewBinding.tvEmpty.visibility = View.VISIBLE
        viewModel.getProductList().observe(this, { products ->
            if (products.isNullOrEmpty()) {
                viewBinding.rvProducts.visibility = View.INVISIBLE
                viewBinding.tvEmpty.visibility = View.VISIBLE
            } else {
                viewBinding.tvEmpty.visibility = View.GONE
                viewBinding.rvProducts.visibility = View.VISIBLE
                populateProducts(products)
            }
        })
        // TODO: 17/07/2021 get bought products from preference 
    }


    override fun onProductClicked(productEntity: ProductEntity) {
        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra(ProductDetailActivity.EXTRAS_PRODUCT, productEntity)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return false
    }

    private fun populateProducts(products: List<ProductEntity>) {
        val productsAdapter = ProductListAdapter(this)
        productsAdapter.setProducts(products)
        with(viewBinding.rvProducts) {
            layoutManager = LinearLayoutManager(this@PurchaseHistoryActivity)
            setHasFixedSize(true)
            adapter = productsAdapter
        }
    }
}