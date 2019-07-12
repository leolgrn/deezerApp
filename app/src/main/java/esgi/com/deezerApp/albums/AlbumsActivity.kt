package esgi.com.deezerApp.albums

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import esgi.com.deezerApp.R
import esgi.com.deezerApp.common.BaseActivity
import esgi.com.deezerApp.data.model.DeezerAlbums

class AlbumsActivity: BaseActivity<AlbumsPresenter, AlbumsView>(R.layout.albums_activity), AlbumsView{

    private lateinit var albumsRecyclerView: RecyclerView
    private var albumsAdapter: AlbumsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        albumsRecyclerView = findViewById(R.id.albums_recyclerview)

        initRecyclerView()
    }

    fun initRecyclerView(){
        albumsAdapter = AlbumsAdapter()
        albumsRecyclerView.adapter = albumsAdapter
        albumsRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun updateData(data: DeezerAlbums) {
        albumsAdapter?.setData(data)
    }

}