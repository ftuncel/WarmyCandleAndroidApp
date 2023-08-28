package com.ferhattuncel.warmycandle.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AboutEntity (@SerializedName("id") var id: Int,
                        @SerializedName("about_title") var title: String,
                        @SerializedName("about_detail") var description: String,
                        @SerializedName("gsm") var gsm: String,
                        @SerializedName("mail") var mail: String,
                        @SerializedName("instagram") var instagram: String,
                        @SerializedName("photo_url") var pic: List<String>): Serializable