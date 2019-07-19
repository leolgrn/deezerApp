package esgi.com.deezerApp.root.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import esgi.com.deezerApp.R
import esgi.com.deezerApp.utils.reactivex.RxBus
import esgi.com.deezerApp.utils.reactivex.RxEvent
import io.reactivex.disposables.Disposable

class PlayerFragment : Fragment() {

    private lateinit var disposable: Disposable
    private lateinit var playerService: PlayerService

    private lateinit var trackTitle: TextView
    private lateinit var albumImage: ImageView
    private lateinit var prevButton: Button
    private lateinit var playPauseButton: Button
    private lateinit var nextButton: Button

    private var isDisplayed = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_player, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        trackTitle = view.findViewById(R.id.player_track_title)
        albumImage = view.findViewById(R.id.player_album_image)
        prevButton = view.findViewById(R.id.player_prev_button)
        playPauseButton = view.findViewById(R.id.player_play_pause_button)
        nextButton = view.findViewById(R.id.player_next_button)

        playerService = PlayerService(playPauseButton, trackTitle)

        disposable = RxBus.listen(RxEvent.EventPlayTrackList::class.java).subscribe {
            if(isDisplayed){
                playerService.setTrackList(it.data, it.position)
            } else {
                playerService.setTrackList(it.data, it.position)
                RxBus.publish(RxEvent.EventDisplayPlayer())
                isDisplayed = true
            }
            setAlbumImage(it.cover)
        }

        prevButton.setOnClickListener{ playerService.previousTrack() }
        playPauseButton.setOnClickListener{ playerService.playOrPauseTrack(playPauseButton) }
        nextButton.setOnClickListener{ playerService.nextTrack() }
    }

    private fun setAlbumImage(cover: String){
        Glide.with(this)
            .load(cover)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(albumImage)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!disposable.isDisposed) disposable.dispose()
    }
}