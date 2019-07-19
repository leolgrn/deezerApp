package esgi.com.deezerApp.root.tracklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import esgi.com.deezerApp.R
import esgi.com.deezerApp.data.model.DeezerTrack
import esgi.com.deezerApp.data.model.DeezerTracklist
import esgi.com.deezerApp.root.Failure
import esgi.com.deezerApp.root.Success
import esgi.com.deezerApp.root.SuccessWithError
import esgi.com.deezerApp.root.tracklist.recyclerview.TracklistAdapter
import esgi.com.deezerApp.utils.reactivex.RxBus
import esgi.com.deezerApp.utils.reactivex.RxEvent

class TracklistFragment : Fragment() {

    private lateinit var tracklistData: ConstraintLayout
    private lateinit var tracklistRecyclerView: RecyclerView
    private lateinit var tracklistAlbumImage: ImageView
    private lateinit var tracklistAlbumTitle: TextView
    private lateinit var tracklistAlbumArtistName: TextView

    private lateinit var tracklistError: ConstraintLayout
    private lateinit var tracklistErrorTextView: TextView

    private var tracklistAdapter: TracklistAdapter? = null
    private lateinit var viewModel: TracklistViewModel

    private val args by navArgs<TracklistFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tracklist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tracklistData = view.findViewById(R.id.tracklist_data)
        tracklistAlbumTitle = view.findViewById(R.id.tracklist_album_title)
        tracklistAlbumArtistName = view.findViewById(R.id.tracklist_artist_name)
        tracklistRecyclerView = view.findViewById(R.id.tracklist_recyclerview)
        tracklistAlbumImage = view.findViewById(R.id.tracklist_album_image)

        tracklistError = view.findViewById(R.id.tracklist_error)
        tracklistErrorTextView = view.findViewById(R.id.tracklist_error_tv)

        initRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(TracklistViewModel::class.java)

        viewModel.tracklistLiveData.observe(viewLifecycleOwner, Observer { dataWrapper ->
            when (dataWrapper) {
                is Success -> updateData(dataWrapper.data)
                is SuccessWithError -> displayError(dataWrapper.error.message)
                is Failure -> displayError(dataWrapper.throwable.message ?: "Unknown Error")
            }
        })

        viewModel.loadTracklist(args.albumId)
    }

    private fun initRecyclerView(){
        tracklistAdapter = TracklistAdapter()
        tracklistAdapter?.setListener(object: TracklistAdapter.ClickListener{
            override fun onClick(data: List<DeezerTrack>, position: Int) {
                RxBus.publish(RxEvent.EventPlayTrackList(data, position, args.albumCover))
            }
        })
        Glide.with(this)
            .load(args.albumCover)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(tracklistAlbumImage)
        tracklistRecyclerView.adapter = tracklistAdapter
        tracklistRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun updateData(data: DeezerTracklist){
        tracklistError.visibility = View.GONE
        tracklistAlbumTitle.text = args.albumTitle
        tracklistAlbumArtistName.text = args.albumArtistName
        tracklistAdapter?.setData(data)
    }

    private fun displayError(error: String){
        tracklistData.visibility = View.GONE
        tracklistErrorTextView.text = error
    }

}