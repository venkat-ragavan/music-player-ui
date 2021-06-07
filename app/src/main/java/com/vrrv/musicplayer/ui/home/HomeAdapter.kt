package com.vrrv.musicplayer.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vrrv.musicplayer.R

internal class HomeAdapter(private var moviesList: List<HomeAlbum>, val row: OnAlbumSelectListener) :
    RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageButton = view.findViewById(R.id.img_album)
        var album: TextView = view.findViewById(R.id.txt_album)
        var artist: TextView = view.findViewById(R.id.txt_artist)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_album_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.image.setImageResource(movie.image)
        holder.album.text = movie.name
        holder.artist.text = movie.artist
        holder.image.setOnClickListener {
            row.selectedAlbum(movie)
        }
        holder.itemView.setOnClickListener {
            row.selectedAlbum(movie)
        }
    }

    override fun getItemCount(): Int = moviesList.count()
}

interface  OnAlbumSelectListener {
    fun selectedAlbum(album: HomeAlbum)
}