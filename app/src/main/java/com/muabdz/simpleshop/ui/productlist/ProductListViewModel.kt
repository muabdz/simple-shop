package com.muabdz.simpleshop.ui.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muabdz.simpleshop.data.ProductEntity

class ProductListViewModel: ViewModel() {

    var products = MutableLiveData<List<ProductEntity>>()

    fun getProductList(): LiveData<List<ProductEntity>> = products
}