package com.vrrv.musicplayer.ui.mylibrary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vrrv.musicplayer.R
import com.vrrv.musicplayer.ui.fullalbum.Songs
import com.vrrv.musicplayer.ui.podcast.Podcast
import com.vrrv.musicplayer.ui.utillity.*

class MyLibraryViewModel : ViewModel() {

    val playingSong: MutableLiveData<Songs> = MutableLiveData()

    val isPlaying = MutableLiveData(false)

    fun getAllPlaylists(): List<String> {
        return listOf("Gem", "Intro", "Divine", "Beatless", "Instrumental", "Duet", "Celebration")
    }

    fun getAllSongs(): List<Songs> {
        return INCEPTION_SONGS + INTERSTELLAR_SONGS + HOURS_SONGS + PEOPLE_LIKE_US_SONGS +
                THE_FLYING_LOTUS_SONGS + HARMONY_WITH_ARR_SONGS + CONNECTIONS_SONGS + LOVER_SONGS +
                UYIRE_SONGS + KANNATHIL_MUTHAMITTAAL_SONGS + JILLUNU_ORU_KADHAL_SONGS + UNNAALE_UNNAALE_SONGS
    }

    fun getAllPodcast(): List<Podcast> {
        return listOf(
            Podcast(R.drawable.pd_vulture, SWITCHED, VULTURE, R.drawable.pd_musicnow, RSMUISC, ROLLING_STONE),
            Podcast(R.drawable.pd_song_secret, XMSONGSOCRET, LOGMEDIA, R.drawable.pd_artscore, ARTOFTHESCORE, ANDREW),
            Podcast(R.drawable.pd_popcast, POPCAST, NEWYORK, R.drawable.pd_desne, HESTIA, DEFNE)
        )
    }

    fun getAllAlbums(): List<LibraryAlbum> {
        return listOf(
            LibraryAlbum(ALBUM, HARMONY_WITH_ARR, R.drawable.album_harmony_with_arr, AR_RAHMAN,"August 15, 2017", ALBUM, LOVER, R.drawable.album_lover, TAYLOR_SWIFT,"June 10, 2019"),
            LibraryAlbum(ALBUM, CONNECTIONS, R.drawable.album_connections, AR_RAHMAN,"July 28, 2010", TAMIL, UYIRE, R.drawable.tam_uyire, AR_RAHMAN,"August 15, 1998"),
            LibraryAlbum(TAMIL, UNNAALE_UNNAALE, R.drawable.tam_unnale_unnale, HARRIS_JAYARAJ,"April 14, 2007", TAMIL, KANNATHIL_MUTHAMITTAAL, R.drawable.tam_kannathil_muthamittaal, AR_RAHMAN,"March 11, 2002"),
            LibraryAlbum(INTERNATIONAL, INTERSTELLAR, R.drawable.int_interstellar, HANS_ZIMMER,"November 10, 2014", INTERNATIONAL, PEOPLE_LIKE_US, R.drawable.int_people_like_us, AR_RAHMAN,"June 19, 2012"),
        )
    }
}

data class LibraryAlbum(
    val categoryLeft: String,
    val nameLeft: String,
    val imageLeft: Int,
    val artistLeft: String,
    val dateLeft: String,
    val categoryRight: String,
    val nameRight: String,
    val imageRight: Int,
    val artistRight: String,
    val dateRight: String,
)