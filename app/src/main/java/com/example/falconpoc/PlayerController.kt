package com.example.falconpoc

import android.content.Context
import android.net.Uri
import android.util.Log
import android.util.Pair
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import java.util.*

class PlayerController(val context: Context): Player.EventListener {
//    var player: SimpleExoPlayer? = null
    private lateinit var playerView: PlayerView
    var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long = 0
    var currentPlayer: SimpleExoPlayer? = null
    private lateinit var currentMetadata: VideoMetaData
    private val fragmentQueue: Queue<SimpleExoPlayer> = LinkedList<SimpleExoPlayer>()

    init {
        for (i in 1..10) {
            fragmentQueue.offer(generateNewPlayer())
        }
    }

    fun initializePlayer(): SimpleExoPlayer {
//        ProcessLifecycleOwner.get().lifecycle.addObserver(lifecycleObserver)
        /* player?.let {
            it.addListener(this)
            return it
        } ?: run {  */
//            val localTrackSelector = DefaultTrackSelector(context, AdaptiveTrackSelection.Factory())
////            trackSelector = localTrackSelector
//
//            val newPlayer = SimpleExoPlayer.Builder(context).apply {
//                setTrackSelector(localTrackSelector)
////                setBandwidthMeter(bandwidthMeter)
//            }.build()
//            newPlayer.playWhenReady = true
////            playWhenReady = true
////            player = newPlayer
//
//            newPlayer.removeListener(this)
//            newPlayer.addListener(this)
//            return newPlayer
//        }
        val newPlayer = fragmentQueue.poll() ?: generateNewPlayer()
        newPlayer.removeListener(this)
        newPlayer.addListener(this)
        currentPlayer = newPlayer
        return newPlayer
    }

    fun generateNewPlayer(): SimpleExoPlayer {
        val localTrackSelector = DefaultTrackSelector(context, AdaptiveTrackSelection.Factory())
//            trackSelector = localTrackSelector

        val newPlayer = SimpleExoPlayer.Builder(context).apply {
            setTrackSelector(localTrackSelector)
//                setBandwidthMeter(bandwidthMeter)
        }.build()
        newPlayer.playWhenReady = true
        return newPlayer
    }

    fun releasePlayer(player: SimpleExoPlayer) {
        player.release()
        fragmentQueue.add(player)
//        progressDisposable?.dispose()
//        ProcessLifecycleOwner.get().lifecycle.removeObserver(lifecycleObserver)
    }

    fun prepareDashMedia(videoMetaData: VideoMetaData) {
        reset()
        currentMetadata = videoMetaData
        currentPlayer?.let {
            it.seekTo(videoMetaData.startPositionMillis)

            val dataSourceFactory: DataSource.Factory = DefaultHttpDataSourceFactory("abc", null)
            val videoSource = ProgressiveMediaSource.Factory(dataSourceFactory, DefaultExtractorsFactory())
                .createMediaSource(Uri.parse(currentMetadata.videoDashUrl))
            it.prepare(videoSource)
        } ?: run {
            Log.e("TAG", "Video Id: ${videoMetaData.videoId}")
            Log.e("TAG", "Error loading video")
        }
    }

    private fun reset() {
//        TODO("Not yet implemented")
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