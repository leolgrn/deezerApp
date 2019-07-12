package esgi.com.deezerApp.data.dto.mapper

import esgi.com.deezerApp.data.dto.EArtist
import esgi.com.deezerApp.data.model.DeezerArtist

class EArtistMapper{
    fun map(eArtist: EArtist): DeezerArtist{
        return DeezerArtist(eArtist.id, eArtist.name, eArtist.picture)
    }
}