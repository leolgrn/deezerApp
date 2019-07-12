package esgi.com.deezerApp.data.dto

import com.google.gson.annotations.SerializedName
import java.net.URL

data class EArtist(
    @SerializedName("id") val id: String,
    @SerializedName("picture") val picture: URL?,
    @SerializedName("name") val name: String
)