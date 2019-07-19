package esgi.com.deezerApp.data.dto.mapper

import esgi.com.deezerApp.data.dto.ETracklistResponse
import esgi.com.deezerApp.data.model.DeezerTrack
import esgi.com.deezerApp.data.model.DeezerTracklist

class ETracklistResponseMapper{
    fun map(eTracklistResponse: ETracklistResponse): DeezerTracklist{
        val tracklist = eTracklistResponse.tracklist?.map {
            val artist = it.artist?.let { EArtistMapper().map(it) }
            DeezerTrack(it.id, it.title, it.preview, artist)
        }
        val error = eTracklistResponse.error?.let { EErrorMapper().map(it) }
        return DeezerTracklist(tracklist, error)
    }
}