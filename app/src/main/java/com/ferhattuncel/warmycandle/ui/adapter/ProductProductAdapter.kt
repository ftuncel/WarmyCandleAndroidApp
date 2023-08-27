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
import com.ferhattuncel.warmycandle.databinding.CardProductProductBinding
import com.ferhattuncel.warmycandle.ui.fragment.ProductPageFragmentDirections
import com.ferhattuncel.warmycandle.ui.viewmodel.ProductViewModel
import com.ferhattuncel.warmycandle.utils.doPageTransfer


class ProductProductAdapter (var mContext:Context, var productList:List<Product>, var viewModel: ProductViewModel)
    : RecyclerView.Adapter<ProductProductAdapter.CardDesignHolder>(){

    inner class CardDesignHolder(var design: CardProductProductBinding) : RecyclerView.ViewHolder(design.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        Log.i("FTLOG", "ProductProductAdapter onCreateViewHolder")

        val binding : CardProductProductBinding = DataBindingUtil
            .inflate(LayoutInflater.from(mContext), R.layout.card_product_product,parent,false)
        return CardDesignHolder(binding)
    }
    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        Log.i("FTLOG", "ProductProductAdapter onBindViewHolder")
        val product = productList.get(position)
        val d = holder.design

        val firstUrl = product.pic.firstOrNull()
        if (firstUrl != null) {
            val fullUrl = "http://warmycandle.com.tr/$firstUrl"
            Log.d("FTLOG", fullUrl)

            Glide.with(mContext)
                .load(fullUrl)
                .override(120, 160)
                .into(d.iwProductTabPic)
        } else {
            // Eğer pic listesi boşsa veya ilk fotoğraf yoksa, alternatif bir resim veya hata işleme yapılabilir.
        }
/*
        val url = "http://warmycandle.com.tr/${product.pic}"
        Log.d("FTLOG", url)
        Glide.with(mContext).load(url).override(120,160).into(d.iwProductTabPic)
*/
        d.productEntityDataBindingVariable = product

        d.cvProductProduct.setOnClickListener(){
            Log.i("FTLOG", "ProductProductAdapter setOnClickListener: Page Transfer to Product Detail")
            val go = ProductPageFragmentDirections.goProductToProductDetail(product = product)
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