package com.vrrv.musicplayer.ui.fullpodcast

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vrrv.musicplayer.BR
import com.vrrv.musicplayer.MainActivity
import com.vrrv.musicplayer.R
import com.vrrv.musicplayer.databinding.FragmentFullPodcastBinding
import com.vrrv.musicplayer.ui.fullalbum.Songs
import com.vrrv.musicplayer.ui.utillity.SharedPreference


class FullPodcastFragment : Fragment(), OnPodcastSelection {

    private lateinit var viewModel: FullPodcastViewModel

    private lateinit var podcastAdapter: FullPodcastAdapter

    private lateinit var binding: FragmentFullPodcastBinding

    private val sharedPreference: SharedPreference by lazy { SharedPreference(MainActivity.instance) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(FullPodcastViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_full_podcast, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()

        if (arguments?.getString("podcast") != null) {
            viewModel.album.value = arguments?.getString("podcast")
            viewModel.podcastData.value = viewModel.getPodcast(arguments?.getString("podcast")!!)
            binding.podcastImage.setBackgroundResource(viewModel.podcastData.value!!.image)
        }

        loadPodcast(binding.root)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (sharedPreference.getSong() != null) {
            Log.d("aaa sha if ", sharedPreference.getSong().toString())
            viewModel.isPlaying.value = true
            viewModel.playingSong.value = Songs(
                sharedPreference.getSong().toString(),
                sharedPreference.getArtists().toString(),
                sharedPreference.getDuration()!!.toString(),
                sharedPreference.getImage()!!,
                sharedPreference.getMovie().toString()
            )
            val playingLayout: View = binding.root.findViewById(R.id.podcast_playing)
            playingLayout.visibility = View.VISIBLE
        }
    }

    private fun loadPodcast(root: View) {
        val recyclerView: RecyclerView = root.findViewById(R.id.rv_podcast_episodes)
        podcastAdapter = FullPodcastAdapter(viewModel.loadEpisodes(), this)
        val mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = podcastAdapter
        podcastAdapter.notifyDataSetChanged()
    }

    override fun selectedPodcast(song: Episode) {
        Log.d("aaa selected episode ", song.toString())

        loadPlayingLayout(song)
        sharedPreference.setSong(song.title)
        sharedPreference.setArtists(viewModel.podcastData.value!!.artist)
        sharedPreference.setDuration(song.duration)
        sharedPreference.setImage(song.image)
        sharedPreference.setMovie(viewModel.album.value.toString())
    }

    private fun loadPlayingLayout(song: Episode) {
        viewModel.isPlaying.value = true
        viewModel.playingSong.value = Songs(song.title, viewModel.podcastData.value!!.artist, song.duration, song.image, viewModel.album.value.toString())
        val playingLayout: View = binding.root.findViewById(R.id.podcast_playing)
        playingLayout.visibility = View.VISIBLE
    }
}
