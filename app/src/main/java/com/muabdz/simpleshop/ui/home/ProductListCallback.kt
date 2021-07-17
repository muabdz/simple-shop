package com.muabdz.simpleshop.ui.home

import com.muabdz.simpleshop.data.ProductEntity

interface ProductListCallback {

    fun onProductClicked(productEntity: ProductEntity)
}