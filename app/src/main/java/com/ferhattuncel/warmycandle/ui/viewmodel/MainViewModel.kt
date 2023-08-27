package com.ferhattuncel.warmycandle.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ferhattuncel.warmycandle.data.entity.Category
import com.ferhattuncel.warmycandle.data.entity.Product
import com.ferhattuncel.warmycandle.data.entity.SliderEntity
import com.ferhattuncel.warmycandle.data.repo.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (var itemRepository: ProductRepository): ViewModel() {
    var offerList = MutableLiveData<List<Product>>()
    private var allOfferItems : List<Product> = listOf()

    var categoryList = MutableLiveData<List<Category>>()
    private var allCategoryItems : List<Category> = listOf()

    var productList = MutableLiveData<List<Product>>()
    private var allProductItems : List<Product> = listOf()

    var sliderList = MutableLiveData<List<SliderEntity>>()
    private var allSliderItems : List<SliderEntity> = listOf()

    init {
        Log.i("FTLOG","MainViewModel init")
        loadOfferItems()
        loadCategoryItems()
        loadProductItems()
        loadSliderItems()
    }

    fun loadProductItems(){
        Log.i("FTLOG","MainViewModel loadProductItems")
        CoroutineScope(Dispatchers.Main).launch {
            allProductItems = itemRepository.loadProductList()
            productList.value = allProductItems
            Log.i("FTLOG","MainViewModel loadProductItems done")
        }
    }

    fun loadSliderItems(){
        Log.i("FTLOG","MainViewModel loadSliderItems")
        CoroutineScope(Dispatchers.Main).launch {
            allSliderItems = itemRepository.loadSliderList()
            sliderList.value = allSliderItems
            Log.i("FTLOG","MainViewModel loadSliderItems done")
        }
    }

    fun loadOfferItems(){
        Log.i("FTLOG","MainViewModel loadOfferItems")
        CoroutineScope(Dispatchers.Main).launch {
            allOfferItems = itemRepository.loadOfferList()
            offerList.value = allOfferItems
            Log.i("FTLOG","MainViewModel loadOfferItems done")
        }
    }

    fun loadCategoryItems(){
        Log.i("FTLOG","MainViewModel loadCategoryItems")
        CoroutineScope(Dispatchers.Main).launch {
            allCategoryItems = itemRepository.loadCategoryList()
            categoryList.value = allCategoryItems
            Log.i("FTLOG","MainViewModel loadCategoryItems done")
        }
    }

    fun filterList(query:String){
        Log.i("FTLOG","MainViewModel filterList")
        CoroutineScope(Dispatchers.Main).launch{
            val filteredItems = if (query.isEmpty()) {
                allOfferItems
            } else {
                allOfferItems.filter { item -> item.name.contains(query, ignoreCase = true) }
            }
            offerList.value = filteredItems
        }
    }
}