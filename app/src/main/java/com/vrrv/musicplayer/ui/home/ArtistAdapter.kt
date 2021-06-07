package com.vrrv.musicplayer.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vrrv.musicplayer.R

internal class ArtistAdapter(private var artists: List<Artist>) :
    RecyclerView.Adapter<ArtistAdapter.MyViewHolder>() {

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageLeft: ImageButton = view.findViewById(R.id.img_left_album)
        var artistLeft: TextView = view.findViewById(R.id.txt_left_artist)
        var imageRight: ImageButton = view.findViewById(R.id.img_right_album)
        var artistRight: TextView = view.findViewById(R.id.txt_right_artist)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_artist_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = artists[position]
        holder.imageLeft.setImageResource(movie.imageLeft)
        holder.artistLeft.text = movie.nameLeft
        holder.imageRight.setImageResource(movie.imageRight)
        holder.artistRight.text = movie.nameRight
    }

    override fun getItemCount(): Int = artists.count()
}