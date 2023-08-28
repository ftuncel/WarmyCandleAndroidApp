package com.ferhattuncel.warmycandle.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ferhattuncel.warmycandle.data.entity.AboutEntity
import com.ferhattuncel.warmycandle.data.entity.Product
import com.ferhattuncel.warmycandle.data.repo.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor (var itemRepository: ProductRepository): ViewModel() {
    var aboutInfo = MutableLiveData<AboutEntity>()
    private var allProductItems : List<Product> = listOf()

    init {
        Log.i("FTLOG","AboutViewModel init")
        loadAboutInfo()
    }

    fun loadAboutInfo(){
        Log.i("FTLOG","AboutViewModel loadAboutInfo")
        CoroutineScope(Dispatchers.Main).launch {
            aboutInfo.value = itemRepository.loadAboutInfo()
        }
    }
}