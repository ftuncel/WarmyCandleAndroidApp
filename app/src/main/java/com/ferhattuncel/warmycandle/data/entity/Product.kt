package com.ferhattuncel.warmycandle.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product (@SerializedName("product_id") var id: Int,
                    @SerializedName("product_name") var name: String,
                    @SerializedName("category_name") var category_name: String,
                    @SerializedName("subcategory_name") var subcategory_name: String,
                    @SerializedName("product_description") var description: String,
                    @SerializedName("product_type") var type: String,
                    @SerializedName("product_color") var color: String,
                    @SerializedName("product_size") var size: String,
                    @SerializedName("product_price") var price: String,
                    @SerializedName("product_stock") var stock: Int,
                    @SerializedName("product_photo_url") var pic: String): Serializable