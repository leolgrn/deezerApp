package esgi.com.deezerApp.data.api

import esgi.com.deezerApp.data.dto.EAlbumsResponse
import esgi.com.deezerApp.data.dto.ETracklistResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DeezerService {
    @GET("user/2529/albums")
    fun getAlbums(@Query("index") index: String): Call<EAlbumsResponse>

    @GET("album/{id}/tracks")
    fun getTracklist(@Path("id") id: String): Call<ETracklistResponse>
}