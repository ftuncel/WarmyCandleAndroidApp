package com.ferhattuncel.warmycandle.retrofit

import com.ferhattuncel.warmycandle.data.entity.CRUDResponse
import com.ferhattuncel.warmycandle.data.entity.ProductPhotoResponse
import com.ferhattuncel.warmycandle.data.entity.ProductResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Field


interface ProductDao {
    @GET("api/getProductList.php")
    suspend fun loadMenuItems() : ProductResponse

    @POST("api/getProductPhoto.php")
    @FormUrlEncoded
    suspend fun loadProductPhotoList(@Field("product_id") product_id: Int) : ProductPhotoResponse

    /*@POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun addToCart(@Field("yemek_adi") itemName:String,
                          @Field("yemek_resim_adi") itemPicture:String,
                          @Field("yemek_fiyat") itemPrice:Int,
                          @Field("yemek_siparis_adet") itemQuantity:Int,
                          @Field("kullanici_adi") username:String) : CRUDResponse

     */
    /*
    @POST("yemekler/sepettekiYemekleriGetir.php")

    @FormUrlEncoded
    suspend fun loadCartItemsList(@Field("kullanici_adi") username: String) : CartResponse
    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun removeCartItem(@Field("sepet_yemek_id") cartItemId:Int,
                               @Field("kullanici_adi") username:String) : CRUDResponse
    */
}