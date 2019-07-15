package esgi.com.deezerApp.root.tracklist

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import esgi.com.deezerApp.R
import esgi.com.deezerApp.data.model.DeezerTrack
import esgi.com.deezerApp.data.model.DeezerTracklist
import esgi.com.deezerApp.root.DeezerMediaPlayer
import esgi.com.deezerApp.root.RootActivity
import esgi.com.deezerApp.root.Success
import esgi.com.deezerApp.tracklist.TracklistAdapter

class TracklistFragment : Fragment() {

    private lateinit var tracklistRecyclerView: RecyclerView
    private var tracklistAdapter: TracklistAdapter? = null
    private lateinit var viewModel: TracklistViewModel

    private val args by navArgs<TracklistFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_tracklist, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tracklistRecyclerView = view.findViewById(R.id.tracklist_recyclerview)

        initRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(TracklistViewModel::class.java)

        viewModel.tracklistLiveData.observe(viewLifecycleOwner, Observer { dataWrapper ->
            when (dataWrapper) {
                is Success -> updateData(dataWrapper.data)
                //is Failure ->
                //is Loading ->
            }
        })

        viewModel.loadTracklist(args.albumId)
    }

    fun initRecyclerView(){
        tracklistAdapter = TracklistAdapter()
        tracklistAdapter?.setListener(object: TracklistAdapter.ClickListener{
            override fun OnClick(data: DeezerTrack) {
                var mediaPlayer = DeezerMediaPlayer.instance
                if(mediaPlayer.isPlaying)
                    mediaPlayer.reset()
                mediaPlayer.setDataSource(data.preview)
                mediaPlayer.prepareAsync()
                mediaPlayer.setOnPreparedListener { p0 -> p0?.start() }
            }
        })
        tracklistRecyclerView.adapter = tracklistAdapter
        tracklistRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    fun updateData(data: DeezerTracklist){
        tracklistAdapter?.setData(data)
    }

}