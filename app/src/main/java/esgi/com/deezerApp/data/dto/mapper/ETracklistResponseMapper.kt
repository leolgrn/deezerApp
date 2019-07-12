package esgi.com.deezerApp.data.dto.mapper

import esgi.com.deezerApp.data.dto.ETracklistResponse
import esgi.com.deezerApp.data.model.DeezerTrack

class ETracklistResponseMapper{
    fun map(eTracklistResponse: ETracklistResponse): List<DeezerTrack>{
        return eTracklistResponse.tracklist.map {
            val artist = it.artist?.let {
                EArtistMapper().map(it)
            }
            DeezerTrack(it.id, it.title, it.preview, artist)
        }
    }
}