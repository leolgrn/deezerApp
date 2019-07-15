package esgi.com.deezerApp.root

import android.media.MediaPlayer

class DeezerMediaPlayer: MediaPlayer() {
    companion object {
        val instance = DeezerMediaPlayer()
    }
}