package esgi.com.deezerApp.root.albums.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
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
        holder.artistName.text = album.deezerArtist?.name ?: ""

        Glide.with(holder.itemView)
            .load(album.cover)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.image)

        ViewCompat.setTransitionName(holder.image,  album.id)

        holder.itemView.setOnClickListener{ listener?.onClick(album, holder.image) }
    }

    class AlbumsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        val title: TextView = itemView.findViewById(R.id.item_album_title)
        val artistName: TextView = itemView.findViewById(R.id.item_album_artist_name)
        val image: AppCompatImageView = itemView.findViewById(R.id.item_album_image)
    }

    interface ClickListener {
        fun onClick(album: DeezerAlbum, albumImage: AppCompatImageView)
    }

}