package com.example.falconpoc

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.*

class VideoFragment(private val video: VideoView): Fragment(), VideoLoadListener {
    var player: SimpleExoPlayer? = null
    private lateinit var playerView: PlayerView
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long = 0

    override fun onStart() {
        super.onStart()

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            initializePlayer(playerView)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.video_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playerView = requireActivity().findViewById(R.id.playerView)
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M || player == null) {
            initializePlayer(playerView)
        }
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            releasePlayer(playerView)
        }
    }

    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            releasePlayer(playerView)
        }
    }

    override fun onVideoReceived(video: VideoView) {
        val dataSourceFactory = DefaultDataSourceFactory(requireContext(), "")
        val videoUri = Uri.parse(video.url)
        val videoSource = DashMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri)
        player?.prepare(videoSource)
    }

    private fun initializePlayer(playerView: PlayerView) {
        val newPlayer = SimpleExoPlayer.Builder(requireContext()).build()
        newPlayer.playWhenReady = playWhenReady
        newPlayer.seekTo(currentWindow, playbackPosition)

        val dataSourceFactory: DataSource.Factory = DefaultHttpDataSourceFactory("abc", null)
        val videoSource = ProgressiveMediaSource.Factory(dataSourceFactory, DefaultExtractorsFactory())
            .createMediaSource(Uri.parse(video.url))
        newPlayer.prepare(videoSource)

        player = newPlayer
        playerView.player = newPlayer
    }


    private fun releasePlayer(playerView: PlayerView) {
        player?.let {
            playWhenReady = it.playWhenReady
            playbackPosition = it.currentPosition
            playerView.player = null
            it.release()
            player = null
        }
    }

}