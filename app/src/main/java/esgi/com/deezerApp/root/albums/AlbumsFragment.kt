package esgi.com.deezerApp.root.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import esgi.com.deezerApp.R
import esgi.com.deezerApp.root.albums.recyclerview.AlbumsAdapter
import esgi.com.deezerApp.data.model.DeezerAlbum
import esgi.com.deezerApp.data.model.DeezerAlbums
import esgi.com.deezerApp.root.Success

class AlbumsFragment : Fragment() {

    private lateinit var albumsRecyclerView: RecyclerView
    private var albumsAdapter: AlbumsAdapter? = null
    private lateinit var viewModel: AlbumsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_albums, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        albumsRecyclerView = view.findViewById(R.id.albums_recyclerview)
        initRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(AlbumsViewModel::class.java)

        viewModel.albumsLiveData.observe(viewLifecycleOwner, Observer { dataWrapper ->
            when (dataWrapper) {
                is Success -> updateData(dataWrapper.data)
                //is Failure ->
                //is Loading ->
            }
        })

        viewModel.loadAlbums()
    }

    private fun initRecyclerView(){
        albumsRecyclerView.apply {
            postponeEnterTransition()
            viewTreeObserver
                .addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }
        }
        albumsAdapter = AlbumsAdapter()
        albumsAdapter?.setListener(object: AlbumsAdapter.ClickListener{
            override fun onClick(album: DeezerAlbum, albumImage: AppCompatImageView) {
                view?.also {
                    val action = AlbumsFragmentDirections.actionAlbumsFragmentToTracklistFragment(
                        album.id,
                        album.title,
                        album.deezerArtist?.name ?: "Unknown artist",
                        album.cover
                    )
                    val extras = FragmentNavigatorExtras(
                        albumImage to "album_image"
                    )
                    Navigation.findNavController(it).navigate(action, extras)
                }
            }
        })
        albumsRecyclerView.adapter = albumsAdapter
        albumsRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun updateData(data: DeezerAlbums){
        albumsAdapter?.setData(data)
    }

}