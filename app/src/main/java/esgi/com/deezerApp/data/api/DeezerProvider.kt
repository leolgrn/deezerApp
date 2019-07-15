package esgi.com.deezerApp.data.api

import esgi.com.deezerApp.BuildConfig
import esgi.com.deezerApp.data.dto.EAlbumsResponse
import esgi.com.deezerApp.data.dto.ETracklistResponse
import esgi.com.deezerApp.data.dto.mapper.EAlbumsResponseMapper
import esgi.com.deezerApp.data.dto.mapper.ETracklistResponseMapper
import esgi.com.deezerApp.data.model.DeezerAlbums
import esgi.com.deezerApp.data.model.DeezerTracklist
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DeezerProvider {
    private var service: DeezerService

    init {
        service = Retrofit.Builder().baseUrl(BuildConfig.DEEZER_API_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DeezerService::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request()
                val url = request.url()

                val urlBuilder = url.newBuilder()
                val newUrl = urlBuilder.build()
                val newRequest = request.newBuilder().url(newUrl).build()
                it.proceed(newRequest)
            }
            .build()
    }

    fun getAlbums(index: String?, listener: Listener<DeezerAlbums>){
        val i = index ?: "0"
        service.getAlbums(i).enqueue(object: Callback<EAlbumsResponse> {
            override fun onFailure(call: Call<EAlbumsResponse>, t: Throwable) {
                listener.onError(t)
            }

            override fun onResponse(call: Call<EAlbumsResponse>, response: Response<EAlbumsResponse>) {
                response.body()?.also {
                    val deezerAlbums = EAlbumsResponseMapper().map(it)
                    listener.onSuccess(deezerAlbums)
                }
            }
        })
    }

    fun getTracklist(id: String, listener: Listener<DeezerTracklist>){
        service.getTracklist(id).enqueue(object: Callback<ETracklistResponse> {
            override fun onFailure(call: Call<ETracklistResponse>, t: Throwable) {
                listener.onError(t)
            }

            override fun onResponse(call: Call<ETracklistResponse>, response: Response<ETracklistResponse>) {
                response.body()?.also {
                    val deezerTrackList = ETracklistResponseMapper().map(it)
                    listener.onSuccess(deezerTrackList)
                }
            }
        })
    }

    interface Listener<T> {
        fun onSuccess(data: T)
        fun onError(t: Throwable)
    }
}

