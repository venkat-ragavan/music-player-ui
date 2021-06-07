package com.vrrv.musicplayer.ui.podcast

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vrrv.musicplayer.BR
import com.vrrv.musicplayer.MainActivity
import com.vrrv.musicplayer.R
import com.vrrv.musicplayer.databinding.FragmentPodcastBinding
import com.vrrv.musicplayer.ui.fullalbum.Songs
import com.vrrv.musicplayer.ui.utillity.MUSIC
import com.vrrv.musicplayer.ui.utillity.SharedPreference
import com.vrrv.musicplayer.ui.utillity.extensions.setPaddingBottom

class PodcastFragment : Fragment(), OnPodcastSelectListener {

    private lateinit var podcastViewModel: PodcastViewModel

    private lateinit var podcastAdapter: PodcastAdapter

    private lateinit var binding: FragmentPodcastBinding

    private val bundle = Bundle()

    private val sharedPreference: SharedPreference by lazy {
        SharedPreference(MainActivity.instance)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        podcastViewModel = ViewModelProvider(this).get(PodcastViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_podcast, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, podcastViewModel)
        binding.executePendingBindings()
        loadPodcast(binding.root)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val podcastLayout: ConstraintLayout = binding.root.findViewById(R.id.podcast_layout)
        context?.let { setPaddingBottom(it, podcastLayout, 60) }
        if (sharedPreference.getSong() != null) {
            Log.d("aaa sha if ", sharedPreference.getSong().toString())
            podcastViewModel.isPlaying.value = true
            podcastViewModel.playingSong.value = Songs(
                sharedPreference.getSong().toString(),
                sharedPreference.getArtists().toString(),
                sharedPreference.getDuration()!!.toString(),
                sharedPreference.getImage()!!,
                sharedPreference.getMovie().toString()
            )
            val playingLayout: View = binding.root.findViewById(R.id.podcast_playing_layout)
            playingLayout.visibility = View.VISIBLE
            context?.let { setPaddingBottom(it, podcastLayout, 120) }
        }
    }

    private fun loadPodcast(root: View) {
        val recyclerView: RecyclerView = root.findViewById(R.id.rv_podcast)
        podcastAdapter = PodcastAdapter(podcastViewModel.preparePodcast(MUSIC), this)
        val mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = podcastAdapter
        podcastAdapter.notifyDataSetChanged()
    }

    override fun selectedPodcast(podcast: String) {
        Log.d("aaa selected podcast ", podcast)
        bundle.putString("podcast", podcast)
        findNavController().navigate(R.id.navigation_full_podcast, bundle)
    }
}