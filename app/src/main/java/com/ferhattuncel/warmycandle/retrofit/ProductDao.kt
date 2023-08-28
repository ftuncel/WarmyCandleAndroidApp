package com.ferhattuncel.warmycandle.retrofit

import com.ferhattuncel.warmycandle.data.entity.AboutResponse
import com.ferhattuncel.warmycandle.data.entity.CategoryResponse
import com.ferhattuncel.warmycandle.data.entity.ProductPhotoResponse
import com.ferhattuncel.warmycandle.data.entity.ProductResponse
import com.ferhattuncel.warmycandle.data.entity.SliderResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Field


interface ProductDao {
    @GET("api/getOfferList.php")
    suspend fun loadOfferItems() : ProductResponse

    @POST("api/getProductPhoto.php")
    @FormUrlEncoded
    suspend fun loadProductPhotoList(@Field("product_id") product_id: Int) : ProductPhotoResponse

    @GET("api/getCategoryList.php")
    suspend fun loadCategoryItems() : CategoryResponse

    @GET("api/getProductList.php")
    suspend fun loadProductItems() : ProductResponse

    @GET("api/getSliderList.php")
    suspend fun loadSliderItems() : SliderResponse

    @GET("api/getAboutInfo.php")
    suspend fun loadAboutInfo() : AboutResponse
}