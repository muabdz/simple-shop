package com.muabdz.simpleshop.ui.productlist.searchproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.muabdz.simpleshop.R
import com.muabdz.simpleshop.data.ProductEntity
import com.muabdz.simpleshop.databinding.ActivitySearchProductBinding
import com.muabdz.simpleshop.ui.productlist.ProductListCallback
import com.muabdz.simpleshop.ui.productdetail.ProductDetailActivity
import com.muabdz.simpleshop.ui.productlist.ProductListAdapter
import com.muabdz.simpleshop.ui.productlist.ProductListViewModel
import com.muabdz.simpleshop.utils.DummyData

class SearchProductActivity : AppCompatActivity(), ProductListCallback {
    private lateinit var viewBinding: ActivitySearchProductBinding
    private lateinit var viewModel: ProductListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySearchProductBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setSupportActionBar(viewBinding.toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ProductListViewModel::class.java]

        viewBinding.rvProducts.visibility = View.INVISIBLE
        viewModel.getProductList().observe(this, { products ->
            if (products.isNullOrEmpty()) {
                viewBinding.rvProducts.visibility = View.INVISIBLE
            } else {
                viewBinding.rvProducts.visibility = View.VISIBLE
                populateSearchProducts(products)
            }
        })

        viewBinding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return false
            }

        })
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

    private fun populateSearchProducts(products: List<ProductEntity>) {
        val productsAdapter = ProductListAdapter(this)
        productsAdapter.setProducts(products)
        with(viewBinding.rvProducts) {
            layoutManager = LinearLayoutManager(this@SearchProductActivity)
            setHasFixedSize(true)
            adapter = productsAdapter
        }
    }

    private fun filter(keyword: String?) {
        val products = ArrayList<ProductEntity>()
        if (!keyword.isNullOrEmpty()) {
            for (product in DummyData.generateDummyProducts()) {
                if (product.title.contains(keyword, true)) {
                    products.add(product)
                }
            }
        }
        viewModel.products.postValue(products)
    }
}