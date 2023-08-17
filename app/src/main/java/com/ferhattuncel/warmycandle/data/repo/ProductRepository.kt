package com.ferhattuncel.warmycandle.data.repo

import android.util.Log
import com.ferhattuncel.warmycandle.data.datasource.ProductDataSource
import com.ferhattuncel.warmycandle.data.entity.Product


class ProductRepository (var productDataSource: ProductDataSource){

    suspend fun incQuantity(quantity:Int) : Int {
        return productDataSource.incQuantity(quantity)
    }

    suspend fun decQuantity(quantity:Int) : Int {
        return productDataSource.decQuantity(quantity)
    }

    suspend fun addToCart(id:Int, name:String, pic:String, price:Int, quantity:Int) {
        //productDataSource.addToCart(id,name,pic,price,quantity)
    }

    suspend fun removeFromCart(id:Int, username:String) {
        productDataSource.removeFromCart(id,username)
    }

    suspend fun loadMenu() : List<Product>{
        Log.e("FTLOG","loadMenu")
        return productDataSource.loadMenu()
    }
    suspend fun loadProductPhotoList(product_id: Int) : List<String>{
        Log.e("FTLOG","loadMenu")
        return productDataSource.loadProductPhotoList(product_id)
    }


    suspend fun cleanCart() {
        //productDataSource.cleanCart()
    }

    suspend fun confirmCart() {
        //productDataSource.confirmCart()
    }
}