package esgi.com.deezerApp.data.dto

import com.google.gson.annotations.SerializedName

data class EError(
    @SerializedName("type") val type: String,
    @SerializedName("message") val message: String,
    @SerializedName("code") val code: Int
)