package com.vrrv.musicplayer.ui.podcast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vrrv.musicplayer.R
import com.vrrv.musicplayer.ui.fullalbum.Songs
import com.vrrv.musicplayer.ui.utillity.*

class PodcastViewModel : ViewModel() {

    val playingSong: MutableLiveData<Songs> = MutableLiveData()

    val isPlaying = MutableLiveData(false)

    fun preparePodcast(type: String): List<Podcast> {
        return when (type) {
            MUSIC -> listOf(
                Podcast(R.drawable.pd_vulture, SWITCHED, VULTURE, R.drawable.pd_musicnow, RSMUISC, ROLLING_STONE),
                Podcast(R.drawable.pd_song_secret, XMSONGSOCRET, LOGMEDIA, R.drawable.pd_artscore, ARTOFTHESCORE, ANDREW),
                Podcast(R.drawable.pd_popcast, POPCAST, NEWYORK, R.drawable.pd_desne, HESTIA, DEFNE),
                Podcast(R.drawable.pd_rain, RAIN, LAURA, R.drawable.pd_meditation, MEDITATION, KITTIKHUN)
            )
            else -> emptyList()
        }
    }

}

data class Podcast(val imageLeft: Int, val nameLeft: String, val artistLeft: String, val imageRight: Int, val nameRight: String, val artistRight: String)

data class PodcastData(val name: String, val artist: String, val image: Int, val description: String, val noOfEpisodes: String)