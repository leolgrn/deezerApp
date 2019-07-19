package esgi.com.deezerApp.root.player

import android.media.MediaPlayer
import android.widget.Button
import android.widget.TextView
import esgi.com.deezerApp.R
import esgi.com.deezerApp.data.model.DeezerTrack

class PlayerService(private var playPauseButton: Button, private var trackTitle: TextView) {

    private var mediaPlayer: MediaPlayer = MediaPlayer()
    private lateinit var tracklist: List<DeezerTrack>
    private var currentPosition: Int = 0

    fun setTrackList(data: List<DeezerTrack>, position: Int){
        this.tracklist = data
        this.currentPosition = position
        play(tracklist[position], playPauseButton)
        setTrackTitle(tracklist[position])
    }

    fun playOrPauseTrack(playPauseButton: Button){
        if(mediaPlayer.isPlaying){
            playPauseButton.setBackgroundResource(R.mipmap.play)
            mediaPlayer.pause()
        }
        else {
            playPauseButton.setBackgroundResource(R.mipmap.pause)
            mediaPlayer.start()
        }
    }

    fun previousTrack(){
        currentPosition--
        if(currentPosition >= 0){
            play(tracklist[currentPosition], playPauseButton)
            setTrackTitle(tracklist[currentPosition])
        }
    }

    fun nextTrack(){
        currentPosition++
        if(currentPosition < tracklist.count()){
            play(tracklist[currentPosition], playPauseButton)
            setTrackTitle(tracklist[currentPosition])
        }
    }

    private fun play(track: DeezerTrack, button: Button){
        if(mediaPlayer.isPlaying || mediaPlayer.currentPosition > 0){
            mediaPlayer.stop()
            mediaPlayer.reset()
        }
        mediaPlayer.setDataSource(track.preview)
        mediaPlayer.prepareAsync()
        mediaPlayer.setOnPreparedListener {
            button.setBackgroundResource(R.mipmap.pause)
            it.start()
        }
    }

    private fun setTrackTitle(track: DeezerTrack){
        trackTitle.text = track.title
    }


}