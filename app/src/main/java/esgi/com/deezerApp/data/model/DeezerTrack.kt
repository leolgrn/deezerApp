package esgi.com.deezerApp.data.model

import java.net.URL

data class DeezerTrack(
    val id: String,
    val title: String,
    val preview: URL,
    val deezerArtist: DeezerArtist?
)