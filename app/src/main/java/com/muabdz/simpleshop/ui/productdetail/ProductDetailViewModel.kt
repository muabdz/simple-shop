package com.muabdz.simpleshop.ui.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muabdz.simpleshop.data.ProductEntity
import com.orhanobut.hawk.Hawk

class ProductDetailViewModel: ViewModel() {

    var product = MutableLiveData<ProductEntity>()

    fun getProductDetail(): LiveData<ProductEntity> = product

    fun purchaseProduct() {
        val purchasedProducts: ArrayList<ProductEntity?> = Hawk.get("purchasedProducts", ArrayList())
        purchasedProducts.add(product.value)
        Hawk.put("purchasedProducts", purchasedProducts)
    }
}