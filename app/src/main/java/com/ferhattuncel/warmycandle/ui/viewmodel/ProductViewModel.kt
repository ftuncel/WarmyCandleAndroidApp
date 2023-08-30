package com.ferhattuncel.warmycandle.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ferhattuncel.warmycandle.data.entity.Category
import com.ferhattuncel.warmycandle.data.entity.Product
import com.ferhattuncel.warmycandle.data.repo.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor (var itemRepository: ProductRepository): ViewModel() {
    var productList = MutableLiveData<List<Product>>()
    private var allProductItems : List<Product> = listOf()

    var categoryList = MutableLiveData<List<Category>>()
    private var allCategoryItems : List<Category> = listOf()

    init {
        Log.i("FTLOG","ProductViewModel init")
        loadProductItems()
        loadCategoryItems()
    }

    fun loadProductItems(){
        Log.i("FTLOG","ProductViewModel loadProductItems")
        CoroutineScope(Dispatchers.Main).launch {
            allProductItems = itemRepository.loadProductList()
            productList.value = allProductItems
        }
    }

    fun loadCategoryItems(){
        Log.i("FTLOG","ProductViewModel loadCategoryItems")
        CoroutineScope(Dispatchers.Main).launch {
            allCategoryItems = itemRepository.loadCategoryList()
            categoryList.value = allCategoryItems
        }
    }

    fun filterList(query:String){
        Log.i("FTLOG","ProductViewModel filterList")
        CoroutineScope(Dispatchers.Main).launch{
            val filteredItems = if (query.isEmpty()) {
                allProductItems
            } else {
                allProductItems.filter { item -> item.name.contains(query, ignoreCase = true) }
            }
            productList.value = filteredItems
        }
    }

    fun filterByCategory(query:String){
        Log.i("FTLOG","ProductViewModel filterCategory with {$query}")
        CoroutineScope(Dispatchers.Main).launch{
            val filteredItems = if (query.isEmpty()) {
                allProductItems
            } else {
                allProductItems.filter { item -> item.category_name.contains(query, ignoreCase = true) }
            }
            Log.d("FTLOG", filteredItems.toString())
            productList.value = filteredItems
        }
    }

    fun filterByName(query: String) {
        Log.i("FTLOG","ProductViewModel filterByName with '{$query}'")
        CoroutineScope(Dispatchers.Main).launch{
            if (query != null) {
                val filteredItems = if (query.isEmpty()) {
                    allProductItems
                } else {
                    allProductItems.filter { item -> item.name.contains(query, ignoreCase = true) }
                }
                Log.d("FTLOG", filteredItems.toString())
                productList.value = filteredItems
            }
        }
    }

    fun clearFilter(){
        Log.i("FTLOG","ProductViewModel clearFilter")
        CoroutineScope(Dispatchers.Main).launch{
            productList.value = allProductItems
        }
    }
}