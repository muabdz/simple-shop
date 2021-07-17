package com.muabdz.simpleshop.data.source

import androidx.lifecycle.LiveData
import com.muabdz.simpleshop.data.HomeDataEntity

interface HomeDataSource {

    fun getHomeData(): LiveData<HomeDataEntity>
}