package com.vrrv.musicplayer.ui.mylibrary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vrrv.musicplayer.R

internal class AllPlaylistAdapter(
    private var playlists: List<String>,
    private val row: OnSelectedPlaylist
) : RecyclerView.Adapter<AllPlaylistAdapter.MyViewHolder>() {

    var selectedRow: Int? = -1

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.txt_playlist_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_my_playlist, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val playListName = playlists[position]
        holder.name.text = playListName
        holder.itemView.setOnClickListener {
            selectedRow = position
            notifyDataSetChanged()
            row.selectedPlaylist(playListName)
        }
    }

    override fun getItemCount(): Int = playlists.count()
}

interface OnSelectedPlaylist {
    fun selectedPlaylist(name: String)
}
