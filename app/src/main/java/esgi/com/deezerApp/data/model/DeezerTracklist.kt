package esgi.com.deezerApp.data.model

import java.net.URL

data class DeezerTracklist(val tracklist: List<DeezerTrack>)

data class DeezerTrack(
    val id: String,
    val title: String,
    val preview: String,
    val deezerArtist: DeezerArtist?
)