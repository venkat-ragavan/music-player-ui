package com.vrrv.musicplayer.ui.home

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vrrv.musicplayer.R
import com.vrrv.musicplayer.ui.fullalbum.Songs
import com.vrrv.musicplayer.ui.utillity.*

class HomeViewModel : ViewModel() {

    val title = getGreetingText()

    val playingSong: MutableLiveData<Songs> = MutableLiveData()

    val isPlaying = MutableLiveData(false)

    fun prepareArtists(): List<Artist> {
        return listOf(
            Artist(R.drawable.taylorswift,  TAYLOR_SWIFT, R.drawable.hanszimmer,  HANS_ZIMMER),
            Artist(R.drawable.arr, AR_RAHMAN, R.drawable.ellie, ELLIE_GOULDING)
        )
    }

    fun prepareAlbums(type: String): List<HomeAlbum> {
        return when (type) {
            ALBUM -> listOf(
                    HomeAlbum(ALBUM, HARMONY_WITH_ARR, R.drawable.album_harmony_with_arr, AR_RAHMAN,"August 15, 2017"),
                    HomeAlbum(ALBUM, LOVER, R.drawable.album_lover, TAYLOR_SWIFT,"June 10, 2019"),
                    HomeAlbum(ALBUM, AETCTBO, R.drawable.album_aetctbo, RAUF_FAIK,"July 28, 2016"),
                    HomeAlbum(ALBUM, THE_FLYING_LOTUS, R.drawable.album_flying_lotus, AR_RAHMAN,"February 11, 2017")
            )
            TAMIL -> listOf(
                    HomeAlbum(TAMIL, UYIRE, R.drawable.tam_uyire, AR_RAHMAN,"August 15, 1998"),
                    HomeAlbum(TAMIL, JILLUNU_ORU_KADHAL, R.drawable.tam_sillunu_oru_kaadhal, AR_RAHMAN,"September 9, 2006"),
                    HomeAlbum(TAMIL, UNNAALE_UNNAALE, R.drawable.tam_unnale_unnale, HARRIS_JAYARAJ,"April 14, 2007"),
                    HomeAlbum(TAMIL, KANNATHIL_MUTHAMITTAAL, R.drawable.tam_kannathil_muthamittaal, AR_RAHMAN,"March 11, 2002")
            )
            INTERNATIONAL -> listOf(
                    HomeAlbum(INTERNATIONAL, INTERSTELLAR, R.drawable.int_interstellar, HANS_ZIMMER,"November 10, 2014"),
                    HomeAlbum(INTERNATIONAL, PEOPLE_LIKE_US, R.drawable.int_people_like_us, AR_RAHMAN,"June 19, 2012"),
                    HomeAlbum(INTERNATIONAL, INCEPTION, R.drawable.int_inception, HANS_ZIMMER,"July 10, 2013"),
                    HomeAlbum(INTERNATIONAL, HOURS, R.drawable.int_127_hours,  AR_RAHMAN,"August 13, 2011")
            )
            else -> emptyList()
        }
    }
}

data class HomeAlbum(
    val category: String,
    val name: String,
    val image: Int,
    val artist: String,
    val date: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readInt(),
            parcel.readString().toString(),
            parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(category)
        parcel.writeString(name)
        parcel.writeInt(image)
        parcel.writeString(artist)
        parcel.writeString(date)
    }

    override fun describeContents(): Int { return 0 }

    companion object CREATOR : Parcelable.Creator<HomeAlbum> {
        override fun createFromParcel(parcel: Parcel): HomeAlbum {
            return HomeAlbum(parcel)
        }

        override fun newArray(size: Int): Array<HomeAlbum?> {
            return arrayOfNulls(size)
        }
    }
}

data class Artist(val imageLeft: Int, val nameLeft: String, val imageRight: Int, val nameRight: String)