package com.muabdz.simpleshop.ui.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muabdz.simpleshop.data.ProductEntity

class ProductDetailViewModel: ViewModel() {

    var product = MutableLiveData<ProductEntity>()

    fun getProductDetail(): LiveData<ProductEntity> = product
}