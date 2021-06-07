package com.vrrv.musicplayer.ui.fullalbum

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vrrv.musicplayer.BR
import com.vrrv.musicplayer.MainActivity
import com.vrrv.musicplayer.R
import com.vrrv.musicplayer.databinding.FragmentFullAlbumBinding
import com.vrrv.musicplayer.ui.home.HomeAlbum
import com.vrrv.musicplayer.ui.utillity.SharedPreference
import com.vrrv.musicplayer.ui.utillity.getYear

class FullAlbumFragment : Fragment(), OnSongSelection {

    private lateinit var viewModel: FullAlbumViewModel

    private lateinit var songsAdapter: SongsAdapter

    private lateinit var binding: FragmentFullAlbumBinding

    private val sharedPreference: SharedPreference by lazy { SharedPreference(MainActivity.instance) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(FullAlbumViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_full_album, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()

        val recyclerView: RecyclerView = binding.root.findViewById(R.id.rv_songs)

        if (arguments?.getParcelable<HomeAlbum>("album") != null) {
            viewModel.album.value = arguments?.getParcelable("album")
            viewModel.year.value = viewModel.album.value?.date?.getYear()
            viewModel.album.value?.image?.let { binding.albumImage.setBackgroundResource(it) }
            val soundtracks = viewModel.getSongs(viewModel.album.value?.name, viewModel.album.value?.category)
            songsAdapter = SongsAdapter(soundtracks, this)
        }

        val mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = songsAdapter

        songsAdapter.notifyDataSetChanged()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (sharedPreference.getSong() != null) {
            Log.d("aaa sha if ", sharedPreference.getSong().toString())
            viewModel.playingSong.value = Songs(
                sharedPreference.getSong().toString(),
                sharedPreference.getArtists().toString(),
                sharedPreference.getDuration()!!.toString(),
                sharedPreference.getImage()!!,
                sharedPreference.getMovie().toString()
            )
            val playingLayout: View = binding.root.findViewById(R.id.playing_layout)
            playingLayout.visibility = View.VISIBLE
        }
    }

    override fun selectedSong(song: Songs) {
        loadPlayingLayout(song)
        Log.d("aaa song ", song.toString())
        sharedPreference.setSong(song.name)
        sharedPreference.setArtists(song.artists)
        sharedPreference.setDuration(song.duration)
        sharedPreference.setImage(song.image)
        sharedPreference.setMovie(song.movie)
    }

    private fun loadPlayingLayout(song: Songs) {
        viewModel.playingSong.value = Songs(song.name, song.artists, song.duration, song.image, song.movie)
        val playingLayout: View = binding.root.findViewById(R.id.playing_layout)
        playingLayout.visibility = View.VISIBLE
    }

}
