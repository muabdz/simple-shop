package com.muabdz.simpleshop.data.source.remote

import com.muabdz.simpleshop.data.source.remote.response.GetDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {

    companion object {

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    fun getHomeData(callback: LoadHomeDataCallback) {
        val client = ApiConfig.getApiService().getApiData()
        client.enqueue(object : Callback<List<GetDataResponse>> {
            override fun onResponse(
                call: Call<List<GetDataResponse>>,
                response: Response<List<GetDataResponse>>
            ) {
                if (response.isSuccessful) {
                    callback.onDataReceived(response.body()!![0])
                } else {
                    callback.onError(response.message())
                }
            }

            override fun onFailure(call: Call<List<GetDataResponse>>, t: Throwable) {
                callback.onError(t.message)
            }
        })
    }

    interface LoadHomeDataCallback {
        fun onDataReceived(dataResponse: GetDataResponse?)

        fun onError(errorMessage: String?)
    }
}