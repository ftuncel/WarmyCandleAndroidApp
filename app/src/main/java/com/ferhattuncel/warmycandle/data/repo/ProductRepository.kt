package com.ferhattuncel.warmycandle.data.repo

import android.util.Log
import com.ferhattuncel.warmycandle.data.datasource.ProductDataSource
import com.ferhattuncel.warmycandle.data.entity.Category
import com.ferhattuncel.warmycandle.data.entity.Product
import com.ferhattuncel.warmycandle.data.entity.SliderEntity


class ProductRepository (var productDataSource: ProductDataSource){

    suspend fun loadOfferList() : List<Product>{
        Log.i("FTLOG","ProductRepository loadOfferList")
        return productDataSource.loadOfferList()
    }

    suspend fun loadProductPhotoList(product_id: Int) : List<String>{
        Log.i("FTLOG","ProductRepository loadProductPhotoList")
        return productDataSource.loadProductPhotoList(product_id)
    }

    suspend fun loadCategoryList() : List<Category>{
        Log.i("FTLOG","ProductRepository loadCategoryList")
        return productDataSource.loadCategoryList()
    }

    suspend fun loadProductList(): List<Product> {
        Log.i("FTLOG","ProductRepository loadProductList")
        return productDataSource.loadProductList()
    }

    suspend fun loadSliderList(): List<SliderEntity> {
        Log.i("FTLOG","ProductRepository loadSliderList")
        return productDataSource.loadSliderList()
    }
}