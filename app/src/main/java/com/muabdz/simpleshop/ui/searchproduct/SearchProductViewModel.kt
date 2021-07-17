package com.muabdz.simpleshop.ui.searchproduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muabdz.simpleshop.data.ProductEntity

class SearchProductViewModel: ViewModel() {

    var products = MutableLiveData<List<ProductEntity>>()

    fun getProductList(): LiveData<List<ProductEntity>> = products
}