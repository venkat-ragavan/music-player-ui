package com.vrrv.musicplayer.ui.fullalbum

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vrrv.musicplayer.ui.home.HomeAlbum
import com.vrrv.musicplayer.ui.utillity.*

class FullAlbumViewModel : ViewModel() {

    val album: MutableLiveData<HomeAlbum> = MutableLiveData(null)

    val year: MutableLiveData<String> = MutableLiveData("")

    val playingSong: MutableLiveData<Songs> = MutableLiveData()

    fun getSongs(name: String?, category: String?): List<Songs> {
        return when (category) {
            INTERNATIONAL -> internationalAlbums[name]!!
            ALBUM -> albums[name]!!
            TAMIL -> tamilAlbums[name]!!
            else -> emptyList()
        }
    }

    private val internationalAlbums = mapOf(
        INCEPTION  to INCEPTION_SONGS,
        INTERSTELLAR to INTERSTELLAR_SONGS,
        HOURS to HOURS_SONGS,
        PEOPLE_LIKE_US to PEOPLE_LIKE_US_SONGS
    )

    private val albums = mapOf(
        THE_FLYING_LOTUS  to THE_FLYING_LOTUS_SONGS,
        HARMONY_WITH_ARR to HARMONY_WITH_ARR_SONGS,
        CONNECTIONS to CONNECTIONS_SONGS,
        LOVER to LOVER_SONGS
    )

    private val tamilAlbums = mapOf(
        UYIRE  to UYIRE_SONGS,
        KANNATHIL_MUTHAMITTAAL to KANNATHIL_MUTHAMITTAAL_SONGS,
        JILLUNU_ORU_KADHAL to JILLUNU_ORU_KADHAL_SONGS,
        UNNAALE_UNNAALE to UNNAALE_UNNAALE_SONGS
    )

}

data class Songs(val name: String, val artists: String, val duration: String, val image: Int, val movie: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Songs

        if (name != other.name) return false
        if (!artists.contentEquals(other.artists)) return false
        if (duration != other.duration) return false
        if (image != other.image) return false
        if (movie != other.movie) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + artists.hashCode()
        result = 31 * result + duration.hashCode()
        result = 31 * result + image
        result = 31 * result + movie.hashCode()

        return result
    }

}