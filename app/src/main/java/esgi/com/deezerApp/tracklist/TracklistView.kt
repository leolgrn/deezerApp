package esgi.com.deezerApp.tracklist

import esgi.com.deezerApp.common.BaseView
import esgi.com.deezerApp.data.model.DeezerTracklist

interface TracklistView: BaseView {
    fun updateData(data: DeezerTracklist)
}