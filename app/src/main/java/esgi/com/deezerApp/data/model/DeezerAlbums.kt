package esgi.com.deezerApp.data.model

import java.net.URL

data class DeezerAlbums(
    val prev: String?,
    val next: String?,
    val albums: List<DeezerAlbum>
)

data class DeezerAlbum(
    val id: String,
    val cover: URL,
    val title: String,
    val deezerArtist: DeezerArtist?
)