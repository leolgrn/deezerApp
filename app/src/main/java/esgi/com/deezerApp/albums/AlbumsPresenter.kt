package esgi.com.deezerApp.albums

import android.util.Log
import esgi.com.deezerApp.common.BasePresenter
import esgi.com.deezerApp.data.api.DeezerProvider
import esgi.com.deezerApp.data.model.DeezerAlbums

class AlbumsPresenter: BasePresenter<AlbumsView>() {
    override fun onCreate() {
        DeezerProvider.getAlbums(null, object : DeezerProvider.Listener<DeezerAlbums> {
            override fun onSuccess(data: DeezerAlbums) {
                view.updateData(data)
            }

            override fun onError(t: Throwable) {
                Log.d("ERROR_ALBUMS", t.message)
            }
        })
    }
}