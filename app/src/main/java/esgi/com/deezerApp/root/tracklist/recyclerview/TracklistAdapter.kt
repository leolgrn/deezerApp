package esgi.com.deezerApp.root.tracklist.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import esgi.com.deezerApp.R
import esgi.com.deezerApp.data.model.DeezerTrack
import esgi.com.deezerApp.data.model.DeezerTracklist

class TracklistAdapter: RecyclerView.Adapter<TracklistAdapter.TracklistViewHolder>(){

    private var data: DeezerTracklist? = null
    private var listener: ClickListener? = null

    fun setData(data: DeezerTracklist) {
        this.data = data
        notifyDataSetChanged()
    }

    fun setListener(listener: ClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TracklistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_track, parent, false)
        return TracklistViewHolder(view)
    }

    override fun getItemCount() = data?.tracklist?.size ?: 0

    override fun onBindViewHolder(holder: TracklistViewHolder, position: Int) {
        data!!.tracklist?.also {
            val track = it[position]
            holder.title.text = track.title
            holder.artistName.text = track.deezerArtist?.name ?: ""
            val list = it
            holder.itemView.setOnClickListener{ listener?.onClick(list, position) }
        }
    }


    class TracklistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.item_track_title)
        val artistName: TextView = itemView.findViewById(R.id.item_track_artist_name)
    }

    interface ClickListener{
        fun onClick(data: List<DeezerTrack>, position: Int)
    }
}