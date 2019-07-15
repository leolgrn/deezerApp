package esgi.com.deezerApp.root.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import esgi.com.deezerApp.data.api.DeezerProvider
import esgi.com.deezerApp.data.model.DeezerAlbums
import esgi.com.deezerApp.root.DataWrapper
import esgi.com.deezerApp.root.Failure
import esgi.com.deezerApp.root.Success
import esgi.com.deezerApp.root.setLoadingState

class AlbumsViewModel : ViewModel() {
    private val albumsMutableLiveData = MutableLiveData<DataWrapper<DeezerAlbums>>()
    val albumsLiveData: LiveData<DataWrapper<DeezerAlbums>> = albumsMutableLiveData

    fun loadAlbums(){
        albumsMutableLiveData.setLoadingState(true)
        DeezerProvider.getAlbums(null, object : DeezerProvider.Listener<DeezerAlbums> {
            override fun onSuccess(data: DeezerAlbums) {
                albumsMutableLiveData.setLoadingState(false)
                albumsMutableLiveData.value = Success(data)
            }

            override fun onError(t: Throwable) {
                albumsMutableLiveData.setLoadingState(false)
                albumsMutableLiveData.value = Failure(t)
            }
        })
    }
}