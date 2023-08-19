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
import com.ferhattuncel.warmycandle.data.entity.Category
import com.ferhattuncel.warmycandle.data.entity.Product
import com.ferhattuncel.warmycandle.databinding.CardCategoryBinding
import com.ferhattuncel.warmycandle.databinding.CardProductBinding
import com.ferhattuncel.warmycandle.ui.viewmodel.MainViewModel
import com.ferhattuncel.warmycandle.utils.doPageTransfer


class CategoryAdapter (var mContext:Context, var categoryList:List<Category>, var viewModel: MainViewModel)
    : RecyclerView.Adapter<CategoryAdapter.CardDesignHolder>(){
    inner class CardDesignHolder(var design: CardCategoryBinding) : RecyclerView.ViewHolder(design.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val binding : CardCategoryBinding = DataBindingUtil
            .inflate(LayoutInflater.from(mContext), R.layout.card_category,parent,false)
        return CardDesignHolder(binding)
    }
    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val category = categoryList.get(position)
        val d = holder.design

        val url = "http://warmycandle.com.tr/${category.pic}"
        Log.e("FTLOG", url)
        Glide.with(mContext).load(url).override(320,200).into(d.iwPicture)

        d.categoryEntityDataBindingVariable = category

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
        return categoryList.size
    }
    fun updateItems(newItems : List<Category>){
        this.categoryList = newItems
        notifyDataSetChanged()
    }
}

/*
class ProductAdapter (var mContext: Context, var productList:List<Product>)
    : RecyclerView.Adapter<ProductAdapter.CardProductHolder>(){

    inner class CardProductHolder (var design : CardProductBinding) : RecyclerView.ViewHolder(design.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardProductHolder {
        val binding = CardProductBinding.inflate(LayoutInflater.from(mContext) ,parent, false)
        return CardProductHolder(binding)
    }

    override fun onBindViewHolder(holder: CardProductHolder, position: Int) {
        val product = productList.get(position)
        val d = holder.design

        d.iwPicture.setImageResource(
            mContext.resources.getIdentifier(product.pic, "drawable", mContext.packageName)
        )

        d.tvPrice.text = "${product.price.toString()} ₺"
        d.btnCart.setOnClickListener {
            Snackbar.make(it, "${product.name} added to cart", Snackbar.LENGTH_SHORT).show()
        }
        d.cvProduct.setOnClickListener{

        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
*/
