package esgi.com.deezerApp.data.dto

import com.google.gson.annotations.SerializedName
import java.net.URL

data class ETracklistResponse(
    @SerializedName("data") val tracklist: List<ETrack>?,
    @SerializedName("error") val error: EError?
)

data class ETrack(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("preview") val preview: String,
    @SerializedName("artist") val artist: EArtist?
)