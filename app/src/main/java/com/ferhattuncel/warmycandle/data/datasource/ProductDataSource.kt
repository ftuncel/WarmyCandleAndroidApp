package com.ferhattuncel.warmycandle.data.datasource

import android.util.Log
import com.ferhattuncel.warmycandle.data.entity.Category
import com.ferhattuncel.warmycandle.data.entity.Product
import com.ferhattuncel.warmycandle.data.entity.SliderEntity
import com.ferhattuncel.warmycandle.retrofit.ProductDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductDataSource(var productDao: ProductDao) {
    suspend fun loadOfferList() : List<Product> =
        withContext(Dispatchers.IO){
            Log.i("FTLOG","ProductDataSource loadOfferList")
            return@withContext productDao.loadOfferItems().products
        }

    suspend fun loadProductPhotoList(product_id:Int) : List<String> =
        withContext(Dispatchers.IO){
            Log.i("FTLOG","ProductDataSource loadProductPhotoList")
            return@withContext productDao.loadProductPhotoList(product_id).product_photo_list
        }

    suspend fun loadCategoryList() : List<Category> =
        withContext(Dispatchers.IO){
            Log.i("FTLOG","ProductDataSource loadCategoryList")
            return@withContext productDao.loadCategoryItems().categories
        }

    suspend fun loadProductList(): List<Product> =
        withContext(Dispatchers.IO){
            Log.i("FTLOG","ProductDataSource loadProductList")
            return@withContext productDao.loadProductItems().products
        }

    suspend fun loadSliderList(): List<SliderEntity> =
        withContext(Dispatchers.IO){
            Log.i("FTLOG","ProductDataSource loadSliderList")
            return@withContext productDao.loadSliderItems().sliders
        }
}