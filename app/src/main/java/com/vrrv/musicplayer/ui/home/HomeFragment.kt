package com.vrrv.musicplayer.ui.home

import android.os.Bundle
import android.util.Log
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
import com.vrrv.musicplayer.BR
import com.vrrv.musicplayer.MainActivity
import com.vrrv.musicplayer.R
import com.vrrv.musicplayer.databinding.FragmentHomeBinding
import com.vrrv.musicplayer.ui.fullalbum.Songs
import com.vrrv.musicplayer.ui.utillity.ALBUM
import com.vrrv.musicplayer.ui.utillity.INTERNATIONAL
import com.vrrv.musicplayer.ui.utillity.SharedPreference
import com.vrrv.musicplayer.ui.utillity.TAMIL

class HomeFragment: Fragment(), OnAlbumSelectListener {

    private lateinit var viewModel: HomeViewModel

    private lateinit var homeAdapter: HomeAdapter

    private lateinit var artistAdapter: ArtistAdapter

    private lateinit var binding: FragmentHomeBinding

    private val bundle = Bundle()

    private val sharedPreference: SharedPreference by lazy {
        SharedPreference(MainActivity.instance)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()

        loadInternational(binding.root)
        loadArtists(binding.root)
        loadAlbums(binding.root)
        loadTamil(binding.root)

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
            val playingLayout: View = binding.root.findViewById(R.id.play_layout)
            playingLayout.visibility = View.VISIBLE
        }
    }

    private fun loadInternational(root: View) {
        val recyclerView: RecyclerView = root.findViewById(R.id.rv_international)
        homeAdapter = HomeAdapter(viewModel.prepareAlbums(INTERNATIONAL), this)
        loadAlbumRecyclerView(recyclerView)
    }

    private fun loadArtists(root: View) {
        val recyclerView: RecyclerView = root.findViewById(R.id.rv_artist)
        artistAdapter = ArtistAdapter(viewModel.prepareArtists())

        val mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = artistAdapter

        artistAdapter.notifyDataSetChanged()
    }

    private fun loadAlbums(root: View) {
        val recyclerView: RecyclerView = root.findViewById(R.id.rv_album)
        homeAdapter = HomeAdapter(viewModel.prepareAlbums(ALBUM), this)
        loadAlbumRecyclerView(recyclerView)
    }

    private fun loadTamil(root: View) {
        val recyclerView: RecyclerView = root.findViewById(R.id.rv_tamil)
        homeAdapter = HomeAdapter(viewModel.prepareAlbums(TAMIL), this)
        loadAlbumRecyclerView(recyclerView)
    }

    private fun loadAlbumRecyclerView(recyclerView: RecyclerView) {
        val mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = homeAdapter
        homeAdapter.notifyDataSetChanged()
    }

    override fun selectedAlbum(album: HomeAlbum) {
        bundle.putParcelable("album", album)
        Log.d("aaa bun ", bundle.getParcelable<HomeAlbum>("album").toString())
        findNavController().navigate(R.id.navigation_full_album, bundle)
    }
}