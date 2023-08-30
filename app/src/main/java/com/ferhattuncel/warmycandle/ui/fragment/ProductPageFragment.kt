package com.ferhattuncel.warmycandle.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import com.ferhattuncel.warmycandle.R
import com.ferhattuncel.warmycandle.databinding.FragmentProductPageBinding
import com.ferhattuncel.warmycandle.ui.adapter.ProductProductAdapter
import com.ferhattuncel.warmycandle.ui.viewmodel.ProductViewModel
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductPageFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentProductPageBinding
    private lateinit var viewModel : ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("FTLOG","ProductPageFragment View")
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_product_page,container,false)
        binding.productPageFragmentDataBindingVariable = this
        binding.productPageToolbarTitle = "Ürünler"

        viewModel.productList.observe(viewLifecycleOwner){
            if (binding.rvProductProduct.adapter == null){
                val adapter = ProductProductAdapter(requireContext(),it,viewModel)
                applyFilter()
                binding.productProductAdapterDataBindingVariable = adapter
            } else {
                (binding.rvProductProduct.adapter as ProductProductAdapter).updateItems(it)
            }
        }

        viewModel.categoryList.observe(viewLifecycleOwner){categoryList ->
            binding.tbCategory.addView(createMaterialButton("Hepsi"))
            categoryList.forEach { category ->
                binding.tbCategory.addView(createMaterialButton(category.name))
            }
        }

        binding.tbCategory.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked){
                val selectedBtn = binding.root.findViewById<MaterialButton>(binding.tbCategory.checkedButtonId)
                val categoryName = selectedBtn.text.toString()
                Log.i("FTLOG","ProductPageFragment Filter for ${categoryName}")
                if (categoryName != "Hepsi") {
                    viewModel.filterByCategory(categoryName)
                } else {
                    viewModel.clearFilter()
                }
            }
        }

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarProductPage)
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu, menu)

                val item = menu.findItem(R.id.action_search)
                val searchView = item.actionView as SearchView
                searchView.setOnQueryTextListener(this@ProductPageFragment) // bu olmazsa arama olmaz
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("FTLOG","ProductPageFragment onCreate")
        super.onCreate(savedInstanceState)
        val tempViewModel: ProductViewModel by viewModels()
        viewModel = tempViewModel
    }

    private fun applyFilter() {
        applyFilterForCategory()
    }

    private fun applyFilterForCategory() {
        val bundle:ProductPageFragmentArgs by navArgs()
        val receivedCategoryName = bundle.categoryName
        Log.d("FTLOG", "receivedCategoryName = $receivedCategoryName")
        if (receivedCategoryName != "ALL_CATEGORIES"){
            viewModel.filterByCategory(receivedCategoryName)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            viewModel.filterByName(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            viewModel.filterByName(newText)
        }
        return true
    }

    private fun createMaterialButton(text: String): MaterialButton {
        val newBtn = MaterialButton(requireContext(),null, com.google.android.material.R.attr.materialButtonOutlinedStyle)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        newBtn.layoutParams = layoutParams
        newBtn.text = text
        newBtn.setTextColor(resources.getColor(R.color.mainBGColor))
        newBtn.setStrokeColorResource(R.color.mainBGColor)

        return newBtn
    }
}