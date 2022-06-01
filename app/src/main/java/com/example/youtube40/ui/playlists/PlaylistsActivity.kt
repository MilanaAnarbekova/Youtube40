package com.example.youtube40.ui.playlists

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtube40.R
import com.example.youtube40.base.BaseActivity
import com.example.youtube40.checkInternet.ConnectionLiveData
import com.example.youtube40.databinding.ActivityPlaylistsBinding
import com.example.youtube40.model.Item

class PlaylistsActivity : BaseActivity<PlaylistViewModel, ActivityPlaylistsBinding>() {

    private lateinit var checkInet: ConnectionLiveData
    private val adapter = PlaylistAdapter()
    override val viewModel: PlaylistViewModel by lazy {
        ViewModelProvider(this)[PlaylistViewModel::class.java]
    }

    companion object {
        const val MY_KEY = "key"
    }

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK && it.data != null) {
                val data = it.data?.getStringExtra(MY_KEY)

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemOnClick()
    }
    override fun initViewModel() {
        viewModel.getPlaylists().observe(this) {
            adapter.setList(it.items as ArrayList<Item>)
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistsBinding {
        return ActivityPlaylistsBinding.inflate(inflater)
    }

    override fun checkInternet() {
        checkInet = ConnectionLiveData(this)
        checkInet.observe(this) {
            if (it) {
                initViewModel()
                binding.layoutNoInternet.root.isInvisible = true
                binding.recyclerPlaylist.isVisible = true
            } else {
                binding.layoutNoInternet.root.isVisible = true
                binding.recyclerPlaylist.isInvisible = true
            }
        }
    }

    override fun initView() {
        binding.recyclerPlaylist.layoutManager = LinearLayoutManager(this)
        binding.recyclerPlaylist.adapter = adapter
    }
    private fun itemOnClick() {
      binding.recyclerPlaylist.setOnClickListener{
          val intent = Intent(this, DetailPlaylistActivity::class.java)
          startActivityForResult.launch(intent)
      }
    }
}