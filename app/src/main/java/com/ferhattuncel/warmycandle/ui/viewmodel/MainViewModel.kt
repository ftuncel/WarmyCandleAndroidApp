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
        Log.e("FTLOG","MainViewModel")
        loadOfferItems()
        loadCategoryItems()
    }

    fun loadProductItems(){
        Log.e("FTLOG","loadProductItems")
        CoroutineScope(Dispatchers.Main).launch {
            allProductItems = itemRepository.loadProductList()
            productList.value = allProductItems
        }
    }

    fun loadSliderItems(){
        Log.e("FTLOG","loadSliderItems")
        CoroutineScope(Dispatchers.Main).launch {
            allSliderItems = itemRepository.loadSliderList()
            sliderList.value = allSliderItems
        }
    }

    fun loadOfferItems(){
        Log.e("FTLOG","loadOfferItems")
        CoroutineScope(Dispatchers.Main).launch {
            allOfferItems = itemRepository.loadOfferList()
            offerList.value = allOfferItems
        }
    }

    fun loadCategoryItems(){
        Log.e("FTLOG","loadCategoryItems")
        CoroutineScope(Dispatchers.Main).launch {
            allCategoryItems = itemRepository.loadCategoryList()
            categoryList.value = allCategoryItems
        }
    }

    fun filterList(query:String){
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