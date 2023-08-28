package com.ferhattuncel.warmycandle.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.ferhattuncel.warmycandle.R
import com.ferhattuncel.warmycandle.databinding.FragmentProductDetailPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailPageFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailPageBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("FTLOG","ProductDetailPageFragment View")

        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_product_detail_page,container,false)
        binding.productDetailPageFragmentDataBindingVariable = this

        val bundle:ProductDetailPageFragmentArgs by navArgs()
        val receivedProduct = bundle.product
        binding.productEntity = receivedProduct

        binding.productDetailPageToolbarTitle = receivedProduct.name


        binding.backButton.setOnClickListener {
            //activity?.onBackPressed()
            Log.w("FTLOG","ProductDetailPageFragment Force back to Main Page")
            Navigation.findNavController(it).navigate(R.id.mainpageFragment)
        }

        val imageList = ArrayList<SlideModel>() // Create image list
        val currentSliderList = receivedProduct.pic // Get the current value of the MutableLiveData
        currentSliderList?.let { sliders ->
            for (picture in sliders) {
                val url = "http://warmycandle.com.tr/${picture}"
                Log.d("FTLOG",url)
                val slideModel = SlideModel(url)
                imageList.add(slideModel)
            }
            binding.productDetailPageImageSlider.setImageList(imageList, ScaleTypes.CENTER_INSIDE)
        }

        return binding.root
    }

/*
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("FTLOG","ProductDetailPageFragment onCreate")
        super.onCreate(savedInstanceState)
        val tempViewModel: ProductSliderViewModel by viewModels()
        viewModel = tempViewModel
    }
*/
}