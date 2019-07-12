package esgi.com.deezerApp.data.dto.mapper

import esgi.com.deezerApp.data.dto.EAlbumsResponse
import esgi.com.deezerApp.data.model.DeezerAlbum
import esgi.com.deezerApp.data.model.DeezerAlbums

class EAlbumsResponseMapper{

    fun map(eAlbumsResponse: EAlbumsResponse): DeezerAlbums{
        val albumList = eAlbumsResponse.albumList.map {
            val artist = it.artist?.let {
                EArtistMapper().map(it)
            }
            DeezerAlbum(it.id, it.cover, it.title, artist)
        }
        return DeezerAlbums(eAlbumsResponse.prev, eAlbumsResponse.next, albumList)
    }

}