package com.muabdz.simpleshop.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.muabdz.simpleshop.data.HomeDataEntity
import com.muabdz.simpleshop.databinding.ActivityHomeBinding
import com.muabdz.simpleshop.ui.MyApplication
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {
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
    }

    private fun populateHome(homeDataEntity: HomeDataEntity) {
        val categoriesAdapter = CategoriesAdapter()
        categoriesAdapter.setCategories(homeDataEntity.categories)
        with(viewBinding.rvCategories) {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = categoriesAdapter
        }
        val productsAdapter = ProductsAdapter()
        productsAdapter.setProducts(homeDataEntity.products)
        with(viewBinding.rvProducts) {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            setHasFixedSize(true)
            adapter = productsAdapter
        }

    }
}