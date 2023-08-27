package com.ferhattuncel.warmycandle.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ferhattuncel.warmycandle.R
import com.ferhattuncel.warmycandle.data.entity.Product
import com.ferhattuncel.warmycandle.databinding.CardProductBinding
import com.ferhattuncel.warmycandle.ui.fragment.MainpageFragmentDirections
import com.ferhattuncel.warmycandle.ui.viewmodel.MainViewModel
import com.ferhattuncel.warmycandle.utils.doPageTransfer


class ProductAdapter (var mContext:Context, var productList:List<Product>, var viewModel: MainViewModel)
    : RecyclerView.Adapter<ProductAdapter.CardDesignHolder>(){

    inner class CardDesignHolder(var design: CardProductBinding) : RecyclerView.ViewHolder(design.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val binding : CardProductBinding = DataBindingUtil
            .inflate(LayoutInflater.from(mContext), R.layout.card_product,parent,false)
        return CardDesignHolder(binding)
    }
    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val product = productList.get(position)
        val d = holder.design

        val firstUrl = product.pic.firstOrNull()
        if (firstUrl != null) {
            val fullUrl = "http://warmycandle.com.tr/$firstUrl"
            Log.d("FTLOG", fullUrl)

            Glide.with(mContext)
                .load(fullUrl)
                .override(120, 160)
                .into(d.iwPicture)
        } else {
            Log.w("FTLOG", "No url found")
        }

        /*
        val url = "http://warmycandle.com.tr/${product.pic[0]}"
        Log.i("FTLOG", url)
        Glide.with(mContext).load(url).override(120,160).into(d.iwPicture)
*/
        d.productEntityDataBindingVariable = product

        d.cvProduct.setOnClickListener(){
            Log.i("FTLOG", "ProductAdapter setOnClickListener. Page Transfer to Product Detail")
            val go = MainpageFragmentDirections.goMainToProductDetail(product = product)
            Navigation.doPageTransfer(it, go)
        }

        /*
        d.btnCart.setOnClickListener {
            val transfer = MainpageFragmentDirections.goProduct(product = product)
            Navigation.doPageTransfer(it,transfer)
        }
        d.iwPicture.setOnClickListener {
            val transfer = MainpageFragmentDirections.goProduct(product = product)
            Navigation.doPageTransfer(it,transfer)
        }
        */
    }

    override fun getItemCount(): Int {
        return productList.size
    }
    fun updateItems(newItems : List<Product>){
        this.productList = newItems
        notifyDataSetChanged()
    }
}