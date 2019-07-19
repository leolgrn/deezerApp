package esgi.com.deezerApp.utils.reactivex

import esgi.com.deezerApp.data.model.DeezerTrack

class RxEvent {
    data class EventPlayTrackList(val data: List<DeezerTrack>, val position: Int, val cover: String)
    class EventDisplayPlayer
}