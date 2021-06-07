package com.vrrv.musicplayer.ui.mylibrary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vrrv.musicplayer.R
import com.vrrv.musicplayer.ui.home.HomeAlbum
import com.vrrv.musicplayer.ui.home.OnAlbumSelectListener

internal class AllAlbumsAdapter(private var allAlbumList: List<LibraryAlbum>, val row: OnLibraryAlbumSelectListener) :
    RecyclerView.Adapter<AllAlbumsAdapter.MyViewHolder>() {

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var leftImage: ImageButton = view.findViewById(R.id.pd_left_image)
        var leftTitle: TextView = view.findViewById(R.id.pd_text_left_title)
        var leftArtist: TextView = view.findViewById(R.id.pd_txt_left_artist)
        var leftLayout: LinearLayout = view.findViewById(R.id.pd_left)


        var rightImage: ImageButton = view.findViewById(R.id.pd_right_image)
        var rightTitle: TextView = view.findViewById(R.id.pd_text_right_title)
        var rightArtist: TextView = view.findViewById(R.id.pd_txt_right_artist)
        var rightLayout: LinearLayout = view.findViewById(R.id.pd_right)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_podcast_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val album = allAlbumList[position]
        holder.leftImage.setImageResource(album.imageLeft)
        holder.leftTitle.text = album.nameLeft
        holder.leftArtist.text = album.artistLeft

        holder.rightImage.setImageResource(album.imageRight)
        holder.rightTitle.text = album.nameRight
        holder.rightArtist.text = album.artistRight

        holder.leftImage.setOnClickListener {
            row.selectedAlbum(
                HomeAlbum(album.categoryLeft, album.nameLeft, album.imageLeft, album.artistLeft, album.dateLeft)
            )
        }
        holder.rightImage.setOnClickListener {
            row.selectedAlbum(
                HomeAlbum(album.categoryRight, album.nameRight, album.imageRight, album.artistRight, album.dateRight)
            )
        }
        holder.leftLayout.setOnClickListener {
            row.selectedAlbum(
                HomeAlbum(album.categoryLeft, album.nameLeft, album.imageLeft, album.artistLeft, album.dateLeft)
            )
        }
        holder.rightLayout.setOnClickListener {
            row.selectedAlbum(
                HomeAlbum(album.categoryRight, album.nameRight, album.imageRight, album.artistRight, album.dateRight)
            )
        }
    }

    override fun getItemCount(): Int = allAlbumList.count()
}

interface OnLibraryAlbumSelectListener {
    fun selectedAlbum(name: HomeAlbum)
}