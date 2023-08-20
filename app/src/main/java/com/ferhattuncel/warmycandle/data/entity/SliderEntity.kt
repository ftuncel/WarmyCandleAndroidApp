package com.ferhattuncel.warmycandle.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SliderEntity (@SerializedName("slider_id") var id: Int,
                         @SerializedName("slider_title") var title: String,
                         @SerializedName("slider_description") var description: String,
                         @SerializedName("slider_url") var pic: String): Serializable