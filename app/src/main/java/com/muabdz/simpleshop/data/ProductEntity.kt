package com.muabdz.simpleshop.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductEntity(
    var id: String,
    var title: String,
    var description: String,
    var imageUrl: String,
    var price: String,
    var loved: Boolean
): Parcelable
