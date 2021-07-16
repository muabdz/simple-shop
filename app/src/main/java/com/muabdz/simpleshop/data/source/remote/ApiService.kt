package com.muabdz.simpleshop.data.source.remote

import com.muabdz.simpleshop.data.source.remote.response.GetDataResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("home")
    fun getApiData(): Call<GetDataResponse>
}