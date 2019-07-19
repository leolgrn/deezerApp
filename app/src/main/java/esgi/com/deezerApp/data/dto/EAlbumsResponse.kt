package esgi.com.deezerApp.data.dto

import com.google.gson.annotations.SerializedName
import java.net.URL

data class EAlbumsResponse(
    @SerializedName("data") val albumList: List<EAlbum>,
    @SerializedName("prev") val prev: String?,
    @SerializedName("next") val next: String?
)

data class EAlbum(
    @SerializedName("id") val id: String,
    @SerializedName("cover") val cover: String,
    @SerializedName("title") val title: String,
    @SerializedName("artist") val artist: EArtist?
)