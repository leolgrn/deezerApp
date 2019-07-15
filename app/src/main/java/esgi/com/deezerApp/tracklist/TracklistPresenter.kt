package esgi.com.deezerApp.tracklist

import android.util.Log
import esgi.com.deezerApp.common.BasePresenter
import esgi.com.deezerApp.data.api.DeezerProvider
import esgi.com.deezerApp.data.model.DeezerTracklist

class TracklistPresenter: BasePresenter<TracklistView>(){
    override fun onCreate() {
        DeezerProvider.getTracklist("123", object: DeezerProvider.Listener<DeezerTracklist> {
            override fun onSuccess(data: DeezerTracklist) {
                view.updateData(data)
            }

            override fun onError(t: Throwable) {
                Log.d("ERROR_TRACKLIST", t.message)
            }
        })
    }
}