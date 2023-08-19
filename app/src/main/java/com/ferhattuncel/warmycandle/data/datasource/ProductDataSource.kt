package com.ferhattuncel.warmycandle.data.datasource

import android.util.Log
import com.ferhattuncel.warmycandle.data.entity.Category
import com.ferhattuncel.warmycandle.data.entity.Product
import com.ferhattuncel.warmycandle.retrofit.ProductDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductDataSource(var productDao: ProductDao) {
    val USER_NAME = "ftuncel"
    suspend fun incQuantity(quantity:Int) : Int =
        withContext(Dispatchers.IO){
            return@withContext (quantity + 1)
        }

    suspend fun decQuantity(quantity:Int) : Int =
        withContext(Dispatchers.IO){
            var newQuantity = quantity - 1
            if (newQuantity < 1) {
                newQuantity = 1
            }
            return@withContext newQuantity
        }

    suspend fun removeFromCart(id:Int, username:String){
        Log.d("FTLOG", "ProductDataSource removeFromCart id= " + id)
        //productDao.removeCartItem(id, username)
    }

    suspend fun loadMenu() : List<Product> =
        withContext(Dispatchers.IO){
            Log.d("FTLOG","ProductDataSource loadMenuItems")
            return@withContext productDao.loadMenuItems().products
        }

    suspend fun loadProductPhotoList(product_id:Int) : List<String> =
        withContext(Dispatchers.IO){
            Log.d("FTLOG","ProductDataSource loadProductPhotoList")
            return@withContext productDao.loadProductPhotoList(product_id).product_photo_list
        }

    suspend fun loadCategoryList() : List<Category> =
        withContext(Dispatchers.IO){
            Log.d("FTLOG","ProductDataSource loadCategoryList")
            return@withContext productDao.loadCategoryItems().categories
        }
}