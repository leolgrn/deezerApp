package esgi.com.deezerApp.albums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import esgi.com.deezerApp.R
import esgi.com.deezerApp.data.model.DeezerAlbum
import esgi.com.deezerApp.data.model.DeezerAlbums

class AlbumsAdapter: RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>(){
    private var data: DeezerAlbums? = null
    private var listener: ClickListener? = null

    fun setData(data: DeezerAlbums) {
        this.data = data
        notifyDataSetChanged()
    }

    fun setListener(listener: ClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumsViewHolder(view)
    }

    override fun getItemCount() = data?.albums?.size ?: 0

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        val album = data?.albums!![position]
        holder.title.text = album.title
        holder.itemView.setOnClickListener{ listener?.OnClick(album) }
    }

    class AlbumsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        val title: TextView = itemView.findViewById(R.id.item_album_title)
    }

    interface ClickListener {
        fun OnClick(album: DeezerAlbum)
    }

}