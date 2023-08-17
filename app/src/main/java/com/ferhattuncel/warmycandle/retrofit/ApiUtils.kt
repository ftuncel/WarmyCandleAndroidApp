package com.ferhattuncel.warmycandle.retrofit

import android.util.Log

class ApiUtils {
    companion object{
        val BASE_URL = "http://warmycandle.com.tr/"

        fun getProductDao() : ProductDao {
            Log.e("FTLOG","ApiUtils getProductDao")
            return RetrofitClient.getClient(BASE_URL).create(ProductDao::class.java)
        }
    }
}