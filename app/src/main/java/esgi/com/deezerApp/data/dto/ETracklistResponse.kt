package esgi.com.deezerApp.data.dto

import com.google.gson.annotations.SerializedName
import java.net.URL

data class ETracklistResponse(@SerializedName("data") val tracklist: List<ETrack>)

data class ETrack(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("preview") val preview: URL,
    @SerializedName("deezerArtist") val artist: EArtist?
)