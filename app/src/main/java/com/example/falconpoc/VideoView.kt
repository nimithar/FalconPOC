package com.example.falconpoc

import android.content.Context
import android.util.AttributeSet
import android.view.SurfaceView
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

class VideoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet, defStyleAttr: Int = 0
) : PlayerView(context, attrs, defStyleAttr) {

    private lateinit var playerController: PlayerController
    private lateinit var video: Video
    private lateinit var playerView: PlayerView

    fun loadCard(video: Video, playerController: PlayerController) {
        this.playerController = playerController
        this.video = video
        this.playerView = this.findViewById(R.id.playerView)
        (playerView.videoSurfaceView as SurfaceView).setZOrderMediaOverlay(true)
        initializePlayer()
    }

    private fun initializePlayer() {
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

    fun releasePlayer() {
        playerController.releasePlayer(video.id, playerView.player as SimpleExoPlayer)
        playerView.player = null
    }
}