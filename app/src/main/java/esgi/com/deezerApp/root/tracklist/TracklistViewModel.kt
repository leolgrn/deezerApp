package esgi.com.deezerApp.root.tracklist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import esgi.com.deezerApp.data.api.DeezerProvider
import esgi.com.deezerApp.data.model.DeezerTracklist
import esgi.com.deezerApp.root.*

class TracklistViewModel : ViewModel() {
    private val tracklistMutableLiveData = MutableLiveData<DataWrapper<DeezerTracklist>>()
    val tracklistLiveData: LiveData<DataWrapper<DeezerTracklist>> = tracklistMutableLiveData

    fun loadTracklist(id: String){
        tracklistMutableLiveData.setLoadingState(true)
        DeezerProvider.getTracklist(id, object : DeezerProvider.Listener<DeezerTracklist> {
            override fun onSuccess(data: DeezerTracklist) {
                tracklistMutableLiveData.setLoadingState(false)
                data.error?.also { tracklistMutableLiveData.value = SuccessWithError(data.error) }
                data.tracklist?.also { tracklistMutableLiveData.value = Success(data) }
            }

            override fun onError(t: Throwable) {
                tracklistMutableLiveData.setLoadingState(false)
                tracklistMutableLiveData.value = Failure(t)
            }
        })
    }

}