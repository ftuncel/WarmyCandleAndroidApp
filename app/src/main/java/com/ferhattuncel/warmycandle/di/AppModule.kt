package com.ferhattuncel.warmycandle.di

import android.util.Log
import com.ferhattuncel.warmycandle.data.datasource.ProductDataSource
import com.ferhattuncel.warmycandle.data.repo.ProductRepository
import com.ferhattuncel.warmycandle.retrofit.ApiUtils
import com.ferhattuncel.warmycandle.retrofit.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providesProductRepository(productDataSource: ProductDataSource) : ProductRepository {
        Log.d("FTLOG","AppModule providesProductRepository")
        return ProductRepository(productDataSource)

    }
    @Provides
    @Singleton
    fun providesProductDataSource(productDao: ProductDao) : ProductDataSource{
        Log.d("FTLOG","AppModule providesProductDataSource")

        return ProductDataSource(productDao)
    }
    @Provides
    @Singleton
    fun providesProductDao() : ProductDao{
        Log.d("FTLOG","AppModule providesProductDao")
        return ApiUtils.getProductDao()
    }

}