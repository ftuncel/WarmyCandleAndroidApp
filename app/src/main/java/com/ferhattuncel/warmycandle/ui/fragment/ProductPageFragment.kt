package com.ferhattuncel.warmycandle.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.ferhattuncel.warmycandle.R
import com.ferhattuncel.warmycandle.databinding.FragmentProductPageBinding
import com.ferhattuncel.warmycandle.ui.adapter.ProductProductAdapter
import com.ferhattuncel.warmycandle.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductPageFragment : Fragment() {
    private lateinit var binding: FragmentProductPageBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("FTLOG","ProductPageFragment")
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_product_page,container,false)
        binding.productPageFragmentDataBindingVariable = this
        binding.productPageToolbarTitle = "Ürünler"

        viewModel.productList.observe(viewLifecycleOwner){
            if (binding.rvProductProduct.adapter == null){
                val adapter = ProductProductAdapter(requireContext(),it,viewModel)
                binding.productProductAdapterDataBindingVariable = adapter
            } else {
                (binding.rvProductProduct.adapter as ProductProductAdapter).updateItems(it)
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("FTLOG","ProductPageFragment onCreate")
        super.onCreate(savedInstanceState)
        val tempViewModel: MainViewModel by viewModels()
        viewModel = tempViewModel
    }

}