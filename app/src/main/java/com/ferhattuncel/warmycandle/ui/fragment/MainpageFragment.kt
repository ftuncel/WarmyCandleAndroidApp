package com.ferhattuncel.warmycandle.ui.fragment

import com.ferhattuncel.warmycandle.databinding.FragmentMainpageBinding
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.ferhattuncel.warmycandle.R
import com.ferhattuncel.warmycandle.ui.adapter.CategoryAdapter
import com.ferhattuncel.warmycandle.ui.adapter.ProductAdapter
import com.ferhattuncel.warmycandle.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainpageFragment : Fragment() {
    private lateinit var binding: FragmentMainpageBinding
    private lateinit var viewModel : MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("FTLOG","MainpageFragment")
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_mainpage,container,false)
        binding.mainpageFragmentDataBindingVariable = this
        binding.mainpageToolbarTitle = "WarmyCandle"

        viewModel.offerList.observe(viewLifecycleOwner){
            if (binding.rvOffer.adapter == null){
                val adapter = ProductAdapter(requireContext(),it,viewModel)
                binding.productAdapterDataBindingVariable = adapter
            } else {
                (binding.rvOffer.adapter as ProductAdapter).updateItems(it)
            }
        }

        viewModel.categoryList.observe(viewLifecycleOwner){
            if (binding.rvCategory.adapter == null){
                val adapter = CategoryAdapter(requireContext(),it,viewModel)
                binding.categoryAdapterDataBindingVariable = adapter
            } else {
                (binding.rvCategory.adapter as CategoryAdapter).updateItems(it)
            }
        }

        /*
        binding.searchBox.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.filterList(newText)
                return true
            }
        })
    */

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: MainViewModel by viewModels()
        viewModel = tempViewModel
    }

    /*fun fabActionGoToCart(it:View){
        Navigation.doPageTransfer(it, R.id.goCart)
    }
    */

}