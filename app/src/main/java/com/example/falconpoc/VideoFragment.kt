package com.example.falconpoc

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.*

class VideoFragment(val video: VideoView) : Fragment(), VideoLoadListener {
    //    var player: SimpleExoPlayer? = null
    private lateinit var playerView: PlayerView
    private lateinit var playerController: PlayerController
//    var playWhenReady = true
//    private var currentWindow = 0
//    private var playbackPosition: Long = 0

//    companion object {
//        private lateinit var video: VideoView
//
//        fun setVideo(video: VideoView) {
//            this.video = video
//        }
//    }

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
        playerController = PlayerController(requireContext())

        playerView = requireActivity().findViewById(R.id.playerView)
    }

    override fun onResume() {
        super.onResume()

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            initializePlayer(playerView)
        }
    }

    override fun onPause() {
        super.onPause()

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            releasePlayer(playerView)
        }
    }

    override fun onVideoReceived(video: VideoView) {
        playerController.prepareDashMedia(
            VideoMetaData(
                videoDashUrl = video.url,
                startPositionMillis = 0,
                progressInMillis = 0,
                videoId = "1",
                durationInMillis = 6000,
                thumbnailUrl = ""
            )
        )
    }

    private fun initializePlayer(playerView: PlayerView) {
        playerView.player = playerController.initializePlayer()
        playerController.prepareDashMedia(VideoMetaData(
            videoDashUrl = video.url,
            startPositionMillis = 0,
            progressInMillis = 0,
            videoId = "1",
            durationInMillis = 6000,
            thumbnailUrl = ""
        ))
    }


    private fun releasePlayer(playerView: PlayerView) {
//        playerView.player = null
//        playerController.releasePlayer()
    }

}