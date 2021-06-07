package com.vrrv.musicplayer.ui.fullalbum

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vrrv.musicplayer.R

internal class SongsAdapter(
    private var songs: List<Songs>,
    private val row: OnSongSelection
) : RecyclerView.Adapter<SongsAdapter.MyViewHolder>() {

    var selectedRow: Int? = -1

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var song: TextView = view.findViewById(R.id.txt_song)
        var artist: TextView = view.findViewById(R.id.txt_artists)
        var options: ImageView = view.findViewById(R.id.img_options)
        var play: ImageView = view.findViewById(R.id.img_play)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_song, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = songs[position]
        val isSelected = selectedRow == position

        holder.song.text = movie.name
        holder.artist.text = movie.artists

        holder.song.setTextColor(if (isSelected) SELECTED else SONG)
        holder.artist.setTextColor(if (isSelected) SELECTED else ARTIST)
        holder.options.setColorFilter(if (isSelected) SELECTED else SONG)
        holder.play.setColorFilter(if (isSelected) SELECTED else SONG)
        holder.play.setImageResource(if (isSelected) R.drawable.ic_baseline_pause_circle_outline_24 else R.drawable.ic_baseline_play_circle_outline_24)

        holder.itemView.setOnClickListener {
            selectedRow = position
            notifyDataSetChanged()
            row.selectedSong(movie)
        }
    }

    override fun getItemCount(): Int = songs.count()

    companion object {
        val SELECTED = Color.parseColor("#00e900")
        val SONG = Color.parseColor("#FFFFFFFF")
        val ARTIST = Color.parseColor("#bababf")
    }
}

interface  OnSongSelection {
    fun selectedSong(song: Songs)
}