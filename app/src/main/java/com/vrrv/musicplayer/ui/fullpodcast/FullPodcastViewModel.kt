package com.vrrv.musicplayer.ui.fullpodcast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vrrv.musicplayer.R
import com.vrrv.musicplayer.ui.fullalbum.Songs
import com.vrrv.musicplayer.ui.podcast.PodcastData
import com.vrrv.musicplayer.ui.utillity.*

class FullPodcastViewModel : ViewModel() {

    val playingSong: MutableLiveData<Songs> = MutableLiveData()

    val album: MutableLiveData<String> = MutableLiveData(null)

    val podcastData: MutableLiveData<PodcastData> = MutableLiveData()

    val isPlaying = MutableLiveData(false)

    fun getPodcast(name: String): PodcastData {
        return when (name) {
            SWITCHED -> PodcastData(SWITCHED, VULTURE, R.drawable.pd_vulture, "Best Arts & Culture podcast Webby 2020 winner about the making and meaning of popular music.", "No. of Episodes: 3")
            RSMUISC -> PodcastData(RSMUISC, ROLLING_STONE, R.drawable.pd_musicnow, "The writers and editors of Rolling Stone take you inside the biggest stories in music.", "No. of Episodes: 3")
            XMSONGSOCRET -> PodcastData(XMSONGSOCRET, LOGMEDIA, R.drawable.pd_song_secret, "Ever wondered how composer Amit Trivedi came up with DevD;s hit Nayan Tarse or the secret behind Rekha Bhardwaj's beautiful Kabira.", "No. of Episodes: 3")
            ARTOFTHESCORE -> PodcastData(ARTOFTHESCORE, ANDREW, R.drawable.pd_artscore, "The podcast that explores, demystifies nd celebrates some of the greatest soundtracks of all time from the world of TV and video games.", "No. of Episodes: 2")
            POPCAST -> PodcastData(POPCAST, NEWYORK, R.drawable.pd_popcast, "Hosted by Jon Caramanica, a pop music critic for the New York Times. ", "No. of Episodes: 1")
            HESTIA -> PodcastData(HESTIA, DEFNE, R.drawable.pd_desne, "Just listen and forget the world.", "No. of Episodes: 2")
            RAIN -> PodcastData(RAIN, LAURA, R.drawable.pd_rain, "Minimal piano with soft rain sound for sleeping.", "No. of Episodes: 2")
            MEDITATION -> PodcastData(MEDITATION, KITTIKHUN, R.drawable.pd_meditation, "Meditation music to calm yourself and soothe the surroundings.", "No. of Episodes: 2")
            else -> PodcastData(SWITCHED, VULTURE, R.drawable.pd_vulture, "Best Arts & Culture podcast Webby 2020 winner about the making and meaning of popular music", "No. of Episodes: 3")
        }
    }

    fun loadEpisodes(): List<Episode> {
        return when (album.value) {
            SWITCHED -> PD_SWITCHED
            RSMUISC -> PD_RSMUISC
            XMSONGSOCRET -> PD_XMSONGSECRET
            ARTOFTHESCORE -> PD_ARTOFTHESCORE
            POPCAST -> PD_POPCAST
            HESTIA -> PD_HESTIA
            RAIN -> PD_RAIN
            MEDITATION -> PD_MEDITATION
            else -> PD_SWITCHED
        }
    }

}

data class Episode(val title: String, val description: String, val image: Int, val releasedDate: String, val duration: String)