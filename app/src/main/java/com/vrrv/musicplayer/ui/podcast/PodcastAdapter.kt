package com.vrrv.musicplayer.ui.podcast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vrrv.musicplayer.R

internal class PodcastAdapter(private var podcastList: List<Podcast>, val row: OnPodcastSelectListener) :
    RecyclerView.Adapter<PodcastAdapter.MyViewHolder>() {

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
        val podcast = podcastList[position]
        holder.leftImage.setImageResource(podcast.imageLeft)
        holder.leftTitle.text = podcast.nameLeft
        holder.leftArtist.text = podcast.artistLeft

        holder.rightImage.setImageResource(podcast.imageRight)
        holder.rightTitle.text = podcast.nameRight
        holder.rightArtist.text = podcast.artistRight

        holder.leftImage.setOnClickListener {
            row.selectedPodcast(podcast.nameLeft)
        }
        holder.rightImage.setOnClickListener {
            row.selectedPodcast(podcast.nameRight)
        }
        holder.leftLayout.setOnClickListener {
            row.selectedPodcast(podcast.nameLeft)
        }
        holder.rightLayout.setOnClickListener {
            row.selectedPodcast(podcast.nameRight)
        }
    }

    override fun getItemCount(): Int = podcastList.count()
}

interface OnPodcastSelectListener {
    fun selectedPodcast(podcast: String)
}