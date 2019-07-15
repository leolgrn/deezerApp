package esgi.com.deezerApp.tracklist

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import esgi.com.deezerApp.R
import esgi.com.deezerApp.common.BaseActivity
import esgi.com.deezerApp.data.model.DeezerTracklist

class TracklistActivity: BaseActivity<TracklistPresenter, TracklistView>(R.layout.activity_tracklist), TracklistView {

    private lateinit var tracklistRecyclerView: RecyclerView
    private var tracklistAdapter: TracklistAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tracklistRecyclerView = findViewById(R.id.tracklist_recyclerview)

        initRecyclerView()
    }

    fun initRecyclerView(){
        tracklistAdapter = TracklistAdapter()
        tracklistRecyclerView.adapter = tracklistAdapter
        tracklistRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun updateData(data: DeezerTracklist) {
        tracklistAdapter?.setData(data)
    }

}