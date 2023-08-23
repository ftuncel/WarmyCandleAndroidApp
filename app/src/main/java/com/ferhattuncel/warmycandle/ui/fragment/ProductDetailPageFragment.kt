package com.ferhattuncel.warmycandle.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ferhattuncel.warmycandle.R
import com.ferhattuncel.warmycandle.databinding.FragmentProductDetailPageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailPageFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailPageBinding
    //private lateinit var viewModel: ProductViewModel
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
            activity?.onBackPressed()
        }

        val url = "http://warmycandle.com.tr/${receivedProduct.pic}"
        Log.e("FTLOG", url)
        Glide.with(binding.root).load(url).override(360,300).into(binding.iwPic)

        return binding.root
    }

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("FTLOG","ProductDetailPageFragment onCreate")
        super.onCreate(savedInstanceState)
        val tempViewModel: ProductViewModel by viewModels()
        viewModel = tempViewModel
    }
    */
}