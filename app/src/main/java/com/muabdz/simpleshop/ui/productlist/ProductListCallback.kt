package com.muabdz.simpleshop.ui.productlist

import com.muabdz.simpleshop.data.ProductEntity

interface ProductListCallback {

    fun onProductClicked(productEntity: ProductEntity)
}