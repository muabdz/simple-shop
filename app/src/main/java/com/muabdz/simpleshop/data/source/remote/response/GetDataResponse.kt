package com.muabdz.simpleshop.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GetDataResponse(

	@field:SerializedName("data")
	val data: Data
)

data class CategoryItem(

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

data class Data(

	@field:SerializedName("productPromo")
	val productPromo: List<ProductPromoItem>,

	@field:SerializedName("category")
	val category: List<CategoryItem>
)

data class ProductPromoItem(

	@field:SerializedName("loved")
	val loved: Int,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("title")
	val title: String
)
