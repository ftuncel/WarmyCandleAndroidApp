package com.ferhattuncel.warmycandle.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.ferhattuncel.warmycandle.R
import com.ferhattuncel.warmycandle.data.entity.AboutEntity
import com.ferhattuncel.warmycandle.databinding.FragmentAboutPageBinding
import com.ferhattuncel.warmycandle.ui.viewmodel.AboutViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutPageFragment : Fragment() {
    private lateinit var binding   : FragmentAboutPageBinding
    private lateinit var viewModel : AboutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("FTLOG","AboutPageFragment View")
        binding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_about_page,container,false)
        binding.aboutPageToolbarTitle = "Hakkımızda"

        viewModel.aboutInfo.observe(viewLifecycleOwner){
            val aboutEntity: AboutEntity? = viewModel.aboutInfo.value

            aboutEntity?.let {
                binding.aboutEntityDataBindingVariable = aboutEntity
                Log.e("FTLOG", "ID: ${it.id}")
                Log.e("FTLOG", "Title: ${it.title}")
                Log.e("FTLOG", "Description: ${it.description}")
                Log.e("FTLOG", "GSM: ${it.gsm}")
                Log.e("FTLOG", "Mail: ${it.mail}")
                Log.e("FTLOG", "Instagram: ${it.instagram}")

                if (it.pic != null) {
                    if (it.pic.isNotEmpty()) {
                        for (url in it.pic) {
                            Log.e("FTLOG", "Photo URL: $url")
                        }
                    } else {
                        Log.e("FTLOG", "Photo URL list is empty")
                    }
                } else {
                    Log.e("FTLOG", "Photo URL list is null")
                }
            } ?: run {
                Log.e("FTLOG", "AboutEntity is null")
            }
        }

        binding.tvAboutInstagram.setOnClickListener(){
            val instagramUrl = binding.aboutEntityDataBindingVariable?.instagram
            instagramUrl?.let { goToWebPage(it) }
        }

        binding.tvAboutWebSite.setOnClickListener(){
            goToWebPage(binding.tvAboutWebSite.toString())
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("FTLOG","AboutPageFragment onCreate")
        super.onCreate(savedInstanceState)
        val tempViewModel: AboutViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun goToWebPage(url: String) {
        Log.d("FTLOG","AboutPageFragment goToWebpage link: '${url}'")
        if (!url.isNullOrEmpty()) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            requireContext().startActivity(intent)
        }
    }
    fun onInstagramClick() {
        Log.d("FTLOG","AboutPageFragment onInstagramClick")
        val instagramUrl = binding.aboutEntityDataBindingVariable?.instagram
        Log.d("FTLOG","AboutPageFragment onInstagramClick link: '${instagramUrl}'")
        if (!instagramUrl.isNullOrEmpty()) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl))
            requireContext().startActivity(intent)
        }
    }

    fun onWebPageClick() {
        Log.d("FTLOG","AboutPageFragment onWebPageClickClick")
        val url = binding.tvAboutWebSite.toString()
        Log.d("FTLOG","AboutPageFragment onWebPageClickClick link: '${url}'")
        if (!url.isNullOrEmpty()) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            requireContext().startActivity(intent)
        }
    }
}