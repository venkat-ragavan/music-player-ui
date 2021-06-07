package com.vrrv.musicplayer.ui.mylibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.vrrv.musicplayer.BR
import com.vrrv.musicplayer.MainActivity
import com.vrrv.musicplayer.R
import com.vrrv.musicplayer.databinding.FragmentMyLibraryBinding
import com.vrrv.musicplayer.ui.fullalbum.OnSongSelection
import com.vrrv.musicplayer.ui.fullalbum.Songs
import com.vrrv.musicplayer.ui.home.HomeAlbum
import com.vrrv.musicplayer.ui.podcast.OnPodcastSelectListener
import com.vrrv.musicplayer.ui.utillity.SharedPreference
import com.vrrv.musicplayer.ui.utillity.extensions.setPaddingBottom

class MyLibraryFragment : Fragment(), OnSongSelection, OnPodcastSelectListener,
    OnLibraryAlbumSelectListener, OnSelectedPlaylist {

    private lateinit var viewModel: MyLibraryViewModel

    private lateinit var binding: FragmentMyLibraryBinding

    lateinit var tabLayout: TabLayout

    private lateinit var allSongsAdapter: AllSongsAdapter

    private lateinit var allPodcastAdapter: AllPodcastAdapter

    private lateinit var allAlbumsAdapter: AllAlbumsAdapter

    private lateinit var allPlaylistAdapter: AllPlaylistAdapter

    private val bundle = Bundle()

    private val sharedPreference: SharedPreference by lazy {
        SharedPreference(MainActivity.instance)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this).get(MyLibraryViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_library, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()

        val songsRecyclerView: RecyclerView = binding.root.findViewById(R.id.rv_all_songs)
        songsRecyclerView.visibility = View.GONE

        val albumRecyclerView: RecyclerView = binding.root.findViewById(R.id.rv_library_album)
        loadPlaylist(albumRecyclerView, songsRecyclerView)

        tabLayout =  binding.root.findViewById(R.id.tab_layout)
        tabLayout.addTab(tabLayout.newTab().setText("Playlist"))
        tabLayout.addTab(tabLayout.newTab().setText("Album"))
        tabLayout.addTab(tabLayout.newTab().setText("Podcast"))
        tabLayout.addTab(tabLayout.newTab().setText("Songs"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                when (tab.position) {
                    0 -> loadPlaylist(albumRecyclerView, songsRecyclerView)
                    1 -> loadAlbums(albumRecyclerView, songsRecyclerView)
                    2 -> loadPodcast(albumRecyclerView, songsRecyclerView)
                    3 -> loadSongs(songsRecyclerView, albumRecyclerView)
                    else -> loadPlaylist(albumRecyclerView, songsRecyclerView)
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val allSongsLayout: RecyclerView = binding.root.findViewById(R.id.rv_all_songs)
        val albumLayout: RecyclerView = binding.root.findViewById(R.id.rv_library_album)
        context?.let { setPaddingBottom(it, allSongsLayout, 0) }
        context?.let { setPaddingBottom(it, albumLayout, 120) }
        if (sharedPreference.getSong() != null) {
            viewModel.isPlaying.value = true
            viewModel.playingSong.value = Songs(
                sharedPreference.getSong().toString(),
                sharedPreference.getArtists().toString(),
                sharedPreference.getDuration()!!.toString(),
                sharedPreference.getImage()!!,
                sharedPreference.getMovie().toString()
            )
            context?.let { setPaddingBottom(it, allSongsLayout, 170) }
            context?.let { setPaddingBottom(it, albumLayout, 170) }
            val playingLayout: View = binding.root.findViewById(R.id.library_play_layout)
            playingLayout.visibility = View.VISIBLE
        }
    }

    private fun loadPodcast(recyclerView: RecyclerView, songsRecyclerView: RecyclerView) {
        songsRecyclerView.visibility = View.GONE
        allPodcastAdapter = AllPodcastAdapter(viewModel.getAllPodcast(), this)
        val mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = allPodcastAdapter
        recyclerView.visibility = View.VISIBLE
        allPodcastAdapter.notifyDataSetChanged()
    }

    private fun loadAlbums(recyclerView: RecyclerView, songsRecyclerView: RecyclerView) {
        songsRecyclerView.visibility = View.GONE
        allAlbumsAdapter = AllAlbumsAdapter(viewModel.getAllAlbums(), this)
        val mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = allAlbumsAdapter
        recyclerView.visibility = View.VISIBLE
        allAlbumsAdapter.notifyDataSetChanged()
    }

    private fun loadPlaylist(recyclerView: RecyclerView, songsRecyclerView: RecyclerView) {
        songsRecyclerView.visibility = View.GONE
        allPlaylistAdapter = AllPlaylistAdapter(viewModel.getAllPlaylists(), this)
        val mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = allPlaylistAdapter
        recyclerView.visibility = View.VISIBLE
        allPlaylistAdapter.notifyDataSetChanged()
    }

    private fun loadSongs(songsRecyclerView: RecyclerView, recyclerView: RecyclerView) {
        recyclerView.visibility = View.GONE
        allSongsAdapter = AllSongsAdapter(viewModel.getAllSongs().sortedBy { it.name }, this)
        val mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        songsRecyclerView.layoutManager = mLayoutManager
        songsRecyclerView.itemAnimator = DefaultItemAnimator()
        songsRecyclerView.adapter = allSongsAdapter
        songsRecyclerView.visibility = View.VISIBLE
        allSongsAdapter.notifyDataSetChanged()
    }

    override fun selectedSong(song: Songs) {
        loadPlayingLayout(song)
        sharedPreference.setSong(song.name)
        sharedPreference.setArtists(song.artists)
        sharedPreference.setDuration(song.duration)
        sharedPreference.setImage(song.image)
        sharedPreference.setMovie(song.movie)
    }

    private fun loadPlayingLayout(song: Songs) {
        viewModel.playingSong.value = Songs(song.name, song.artists, song.duration, song.image, song.movie)
        val playingLayout: View = binding.root.findViewById(R.id.library_play_layout)
        playingLayout.visibility = View.VISIBLE
    }

    override fun selectedPodcast(podcast: String) {
        bundle.putString("podcast", podcast)
        findNavController().navigate(R.id.navigation_full_podcast, bundle)
    }

    override fun selectedAlbum(album: HomeAlbum) {
        bundle.putParcelable("album", album)
        findNavController().navigate(R.id.navigation_full_album, bundle)
    }

    override fun selectedPlaylist(name: String) {}

}