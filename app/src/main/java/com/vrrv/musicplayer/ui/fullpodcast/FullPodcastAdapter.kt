package com.vrrv.musicplayer.ui.fullpodcast

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vrrv.musicplayer.R

internal class FullPodcastAdapter(
    private var podcasts: List<Episode>,
    private val row: OnPodcastSelection
) : RecyclerView.Adapter<FullPodcastAdapter.MyViewHolder>() {

    var selectedRow: Int? = -1

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.img_episode)
        var name: TextView = view.findViewById(R.id.tv_episode_name)
        var description: TextView = view.findViewById(R.id.tv_episode_description)
        var date: TextView = view.findViewById(R.id.tv_date)
        var duration: TextView = view.findViewById(R.id.tv_time)
        var play: ImageView = view.findViewById(R.id.img_play)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_podcast_episode, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pod = podcasts[position]
        val isSelected = selectedRow == position

        holder.image.setImageResource(pod.image)
        holder.name.text = pod.title
        holder.description.text = pod.description
        holder.date.text = pod.releasedDate
        holder.duration.text = pod.duration.toString()
        holder.name.setTextColor(if (isSelected) SELECTED else SONG)
        holder.play.setColorFilter(if (isSelected) SELECTED else SONG)
        holder.play.setImageResource(if (isSelected) R.drawable.ic_baseline_pause_circle_outline_24 else R.drawable.ic_baseline_play_circle_outline_24)

        holder.itemView.setOnClickListener {
            selectedRow = position
            notifyDataSetChanged()
            row.selectedPodcast(pod)
        }
    }

    override fun getItemCount(): Int = podcasts.count()

    companion object {
        val SELECTED = Color.parseColor("#00e900")
        val SONG = Color.parseColor("#FFFFFFFF")
    }
}

interface  OnPodcastSelection {
    fun selectedPodcast(song: Episode)
}