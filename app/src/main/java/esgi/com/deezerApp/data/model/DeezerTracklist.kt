package esgi.com.deezerApp.data.model

data class DeezerTracklist(
    val tracklist: List<DeezerTrack>?,
    val error: DeezerError?
)

data class DeezerTrack(
    val id: String,
    val title: String,
    val preview: String,
    val deezerArtist: DeezerArtist?
)