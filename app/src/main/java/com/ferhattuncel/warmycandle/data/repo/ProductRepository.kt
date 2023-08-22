package com.ferhattuncel.warmycandle.data.repo

import android.util.Log
import com.ferhattuncel.warmycandle.data.datasource.ProductDataSource
import com.ferhattuncel.warmycandle.data.entity.Category
import com.ferhattuncel.warmycandle.data.entity.Product
import com.ferhattuncel.warmycandle.data.entity.SliderEntity


class ProductRepository (var productDataSource: ProductDataSource){

    suspend fun loadOfferList() : List<Product>{
        Log.e("FTLOG"," ProductRepository loadOfferList")
        return productDataSource.loadOfferList()
    }
    suspend fun loadProductPhotoList(product_id: Int) : List<String>{
        Log.e("FTLOG"," ProductRepository loadProductPhotoList")
        return productDataSource.loadProductPhotoList(product_id)
    }

    suspend fun loadCategoryList() : List<Category>{
        Log.e("FTLOG","ProductRepository loadCategoryList")
        return productDataSource.loadCategoryList()
    }

    suspend fun loadProductList(): List<Product> {
        Log.e("FTLOG","ProductRepository loadProductList")
        return productDataSource.loadProductList()
    }

    suspend fun loadSliderList(): List<SliderEntity> {
        Log.e("FTLOG","ProductRepository loadProductList")
        return productDataSource.loadSliderList()
    }


}