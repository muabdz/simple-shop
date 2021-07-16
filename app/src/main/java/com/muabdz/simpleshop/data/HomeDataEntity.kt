package com.muabdz.simpleshop.data

data class HomeDataEntity(
    var categories: List<CategoryEntity>?,
    var products: List<ProductEntity>?
) {
    var errorMessage: String? = null
}
