package com.muabdz.simpleshop.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.muabdz.simpleshop.data.HomeDataEntity
import com.muabdz.simpleshop.data.ProductEntity
import com.muabdz.simpleshop.databinding.ActivityHomeBinding
import com.muabdz.simpleshop.ui.MyApplication
import com.muabdz.simpleshop.ui.productdetail.ProductDetailActivity
import com.muabdz.simpleshop.ui.searchproduct.SearchProductActivity
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), ProductListCallback {
    private lateinit var viewBinding: ActivityHomeBinding
    @Inject lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as MyApplication).appComponent.inject(this)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setSupportActionBar(viewBinding.toolbar)
        viewBinding.progressBar.visibility = View.VISIBLE
        viewBinding.nsvHome.visibility = View.INVISIBLE
        viewModel.getHomeData().observe(this, { homeData ->
            viewBinding.progressBar.visibility = View.GONE
            if (homeData.errorMessage.isNullOrEmpty()) {
                viewBinding.nsvHome.visibility = View.VISIBLE
                populateHome(homeData)
            } else {
                viewBinding.tvError.visibility = View.VISIBLE
            }
        })

        viewBinding.searchBar.setOnClickListener {
            val intent = Intent(this, SearchProductActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onProductClicked(productEntity: ProductEntity) {
        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra(ProductDetailActivity.EXTRAS_PRODUCT, productEntity)
        startActivity(intent)
    }

    private fun populateHome(homeDataEntity: HomeDataEntity) {
        val categoriesAdapter = CategoriesAdapter()
        categoriesAdapter.setCategories(homeDataEntity.categories)
        with(viewBinding.rvCategories) {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = categoriesAdapter
        }
        val productsAdapter = ProductsAdapter(this)
        productsAdapter.setProducts(homeDataEntity.products)
        with(viewBinding.rvProducts) {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            setHasFixedSize(true)
            adapter = productsAdapter
        }

    }
}