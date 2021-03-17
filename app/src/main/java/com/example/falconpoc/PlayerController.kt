package com.example.falconpoc

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory

class PlayerController(private val context: Context): Player.EventListener {
    private var currentPlayer: SimpleExoPlayer? = null
    private lateinit var currentMetadata: VideoMetaData
    private val videoPlayerMap = mutableMapOf<String, SimpleExoPlayer>()

    fun initializePlayer(): SimpleExoPlayer {
        val newPlayer= generateNewPlayer()
        newPlayer.removeListener(this)
        newPlayer.addListener(this)
        currentPlayer = newPlayer
        return newPlayer
    }

    private fun generateNewPlayer(): SimpleExoPlayer {
        val localTrackSelector = DefaultTrackSelector(context, AdaptiveTrackSelection.Factory())

        return SimpleExoPlayer.Builder(context).apply {
            setTrackSelector(localTrackSelector)
        }.build()
    }

    fun releasePlayer(videoId: String, player: SimpleExoPlayer) {
        videoPlayerMap.remove(videoId)
        player.release()
    }

    fun prepareDashMedia(videoMetaData: VideoMetaData) {
        currentMetadata = videoMetaData
        currentPlayer?.let {
            it.seekTo(videoMetaData.startPositionMillis)

            val dataSourceFactory: DataSource.Factory = DefaultHttpDataSourceFactory("abc", null)
            val videoSource = ProgressiveMediaSource.Factory(dataSourceFactory, DefaultExtractorsFactory())
                .createMediaSource(Uri.parse(currentMetadata.videoDashUrl))
            videoPlayerMap[videoMetaData.videoId] = it
            it.prepare(videoSource)
        } ?: run {
            Log.e("TAG", "Video Id: ${videoMetaData.videoId}")
            Log.e("TAG", "Error loading video")
        }
    }

    fun play(player: SimpleExoPlayer) {
        player.playWhenReady = true
    }

    fun pause(player: SimpleExoPlayer) {
        player.playWhenReady = false
    }
}

data class VideoMetaData(
    val videoId: String,
    val videoDashUrl: String,
    val startPositionMillis: Long,
    val durationInMillis: Long,
    var progressInMillis: Long,
    val thumbnailUrl: String? = null
)