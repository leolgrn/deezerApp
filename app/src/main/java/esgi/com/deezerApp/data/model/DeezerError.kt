package esgi.com.deezerApp.data.model

data class DeezerError(
    val type: String,
    val message: String,
    val code: Int
)