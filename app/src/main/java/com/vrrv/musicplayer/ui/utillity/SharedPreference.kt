package com.vrrv.musicplayer.ui.utillity

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(val context: Context): IPreferenceHelper {

    private val PREFS_NAME = "soul"

    private val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        const val SONG = "song"
        const val ARTISTS = "artists"
        const val IMAGE = "image"
        const val DURATION = "duration"
        const val MOVIE = "Movie"
    }

    override fun setSong(song: String) {
        sharedPref[SONG] = song
    }

    override fun getSong(): String? {
        return sharedPref[SONG]
    }

    override fun setArtists(artists: String) {
        sharedPref[ARTISTS] = artists
    }

    override fun getArtists(): String? {
        return sharedPref[ARTISTS]
    }

    override fun setImage(image: Int) {
        sharedPref[IMAGE] = image
    }

    override fun getImage(): Int? {
        return sharedPref[IMAGE]
    }

    override fun setDuration(duration: String) {
        sharedPref[DURATION] = duration
    }

    override fun getDuration(): String? {
        return sharedPref[DURATION]
    }

    override fun setMovie(movie: String) {
        sharedPref[MOVIE] = movie
    }

    override fun getMovie(): String? {
        return sharedPref[MOVIE]
    }

    override fun clearPrefs() {
        sharedPref.edit().clear().apply()
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    private operator fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is String? -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Long -> edit { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    private inline operator fun <reified T : Any> SharedPreferences.get(
        key: String,
        defaultValue: T? = null
    ): T? {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

}

interface IPreferenceHelper {
    fun setSong(song: String)
    fun getSong(): String?
    fun setArtists(artists: String)
    fun getArtists(): String?
    fun setImage(image: Int)
    fun getImage(): Int?
    fun setDuration(duration: String)
    fun getDuration(): String?
    fun setMovie(movie: String)
    fun getMovie(): String?
    fun clearPrefs()
}