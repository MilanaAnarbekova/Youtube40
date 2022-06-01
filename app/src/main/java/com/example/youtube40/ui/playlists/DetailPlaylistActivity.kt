package com.example.youtube40.ui.playlists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.youtube40.R
import com.example.youtube40.base.BaseActivity
import com.example.youtube40.databinding.ActivityPlaylistsBinding

class DetailPlaylistActivity : BaseActivity<PlaylistViewModel, ActivityPlaylistsBinding>()  {

    companion object {
        const val MY_KEY = "key"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_playlist)
        val data = intent.getStringExtra(MY_KEY).toString()
    }

    override val viewModel: PlaylistViewModel
        get() = TODO("Not yet implemented")

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistsBinding {
        TODO("Not yet implemented")
    }


}