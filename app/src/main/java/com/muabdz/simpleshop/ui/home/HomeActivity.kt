package com.muabdz.simpleshop.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.muabdz.simpleshop.R
import com.muabdz.simpleshop.data.HomeDataEntity
import com.muabdz.simpleshop.data.ProductEntity
import com.muabdz.simpleshop.databinding.ActivityHomeBinding
import com.muabdz.simpleshop.ui.MyApplication
import com.muabdz.simpleshop.ui.productdetail.ProductDetailActivity
import com.muabdz.simpleshop.ui.productlist.ProductListCallback
import com.muabdz.simpleshop.ui.productlist.purchasehistory.PurchaseHistoryActivity
import com.muabdz.simpleshop.ui.productlist.searchproduct.SearchProductActivity
import com.muabdz.simpleshop.utils.GlobalIdlingResource
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
        // TODO: 18/07/2021 code below is for testing purposes only, remove when app released
        GlobalIdlingResource.increment()
        viewModel.getHomeData().observe(this, { homeData ->
            viewBinding.progressBar.visibility = View.GONE
            if (homeData.errorMessage.isNullOrEmpty()) {
                viewBinding.nsvHome.visibility = View.VISIBLE
                populateHome(homeData)
            } else {
                viewBinding.tvError.visibility = View.VISIBLE
            }
            // TODO: 18/07/2021 code below is for testing purposes only, remove when app released
            if (!GlobalIdlingResource.getIdlingResource().isIdleNow) {
                GlobalIdlingResource.decrement()
            }
        })

        viewBinding.searchBar.setOnClickListener {
            val intent = Intent(this, SearchProductActivity::class.java)
            startActivity(intent)
        }

        viewBinding.bottomNavigation.setOnItemSelectedListener { item ->
            if (item.itemId == R.id.bn_profile) {
                val intent = Intent(this@HomeActivity, PurchaseHistoryActivity::class.java)
                startActivity(intent)
            }
            false
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