package esgi.com.deezerApp.albums

import esgi.com.deezerApp.common.BaseView
import esgi.com.deezerApp.data.model.DeezerAlbums

interface AlbumsView: BaseView {
    fun updateData(data: DeezerAlbums)
}