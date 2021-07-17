package com.muabdz.simpleshop.ui.home

import androidx.lifecycle.LiveData
import com.muabdz.simpleshop.data.HomeDataEntity
import com.muabdz.simpleshop.data.source.HomeRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) {

    fun getHomeData() : LiveData<HomeDataEntity> = homeRepository.getHomeData()
}