package com.muabdz.simpleshop.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.muabdz.simpleshop.data.CategoryEntity
import com.muabdz.simpleshop.data.HomeDataEntity
import com.muabdz.simpleshop.data.ProductEntity
import com.muabdz.simpleshop.data.source.remote.RemoteDataSource
import com.muabdz.simpleshop.data.source.remote.response.GetDataResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(private val remoteDataSource: RemoteDataSource): HomeDataSource {

    override fun getHomeData(): LiveData<HomeDataEntity> {
        val homeData = MutableLiveData<HomeDataEntity>()
        remoteDataSource.getHomeData(object : RemoteDataSource.LoadHomeDataCallback {

            override fun onDataReceived(dataResponse: GetDataResponse?) {
                val categoryList = ArrayList<CategoryEntity>()
                val productList = ArrayList<ProductEntity>()
                if (dataResponse != null) {
                    val categories = dataResponse.data.category
                    for (category in categories) {
                        val categoryEntity = CategoryEntity(
                            category.id,
                            category.name,
                            category.imageUrl
                        )
                        categoryList.add(categoryEntity)
                    }
                    val products = dataResponse.data.productPromo
                    for (product in products) {
                        val productEntity = ProductEntity(
                            product.id,
                            product.title,
                            product.description,
                            product.imageUrl,
                            product.price,
                            product.loved == 1
                        )
                        productList.add(productEntity)
                    }
                }
                val homeDataEntity = HomeDataEntity(
                    categoryList,
                    productList
                )
                homeData.postValue(homeDataEntity)
            }

            override fun onError(errorMessage: String?) {
                val homeDataEntity = HomeDataEntity(null, null)
                homeDataEntity.errorMessage = errorMessage
                homeData.postValue(homeDataEntity)
            }

        })
        return homeData
    }
}