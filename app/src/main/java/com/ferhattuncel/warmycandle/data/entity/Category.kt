package com.ferhattuncel.warmycandle.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Category (@SerializedName("category_id") var id: Int,
                     @SerializedName("category_name") var name: String,
                     @SerializedName("category_photo_url") var pic: String): Serializable