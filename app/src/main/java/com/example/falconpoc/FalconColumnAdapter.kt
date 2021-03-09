package com.example.falconpoc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import java.util.*

class FalconColumnAdapter(private val activity: FragmentActivity, val playerController: PlayerController) :
    RecyclerView.Adapter<FalconColumnAdapter.VideoViewHolder>() {
    private val items = mutableListOf<VideoView>()
    var currentItem: VideoView? = null
    private val fragmentQueue: Queue<Fragment> = LinkedList<Fragment>()

    init {
//        for (i in 1..10) {
//            fragmentQueue.offer(VideoFragment())
//        }
    }

    fun setVideos(videos: List<VideoView>) {
        items.clear()
        items.addAll(videos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            LayoutInflater.from(activity).inflate(R.layout.fragment_container, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class VideoViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val playerView: PlayerView = v.findViewById(R.id.playerView)
        fun bind(video: VideoView) {
            currentItem = video

            initializePlayer(playerView)
        }
    }

    private fun initializePlayer(playerView: PlayerView) {
        val localCurrentItem = currentItem ?: return
        playerView.player = playerController.initializePlayer()
        playerController.prepareDashMedia(VideoMetaData(
            videoDashUrl = localCurrentItem.url,
            startPositionMillis = 0,
            progressInMillis = 0,
            videoId = "1",
            durationInMillis = 6000,
            thumbnailUrl = ""
        ))
    }


    private fun releasePlayer(playerView: PlayerView) {
        playerController.releasePlayer(playerView.player as SimpleExoPlayer)
        playerView.player = null
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

//        currentItem?.let { addFragment(VideoFragment(it)) }
    }

    override fun onViewRecycled(holder: VideoViewHolder) {
        super.onViewRecycled(holder)
        releasePlayer(holder.playerView)

    }
//
//    override fun onViewAttachedToWindow(holder: VideoViewHolder) {
//        attachFragmentToContainer(R.id.fragmentContainer)
//
//        super.onViewAttachedToWindow(holder)
//    }
//
//    private fun attachFragmentToContainer(itemId: Int) {
//        val localCurrentItem = currentItem ?: return
//        val fragment = if (activity.supportFragmentManager.fragments.firstOrNull { it is VideoFragment } == null) {
//            VideoFragment(localCurrentItem)
//        } else {
//            null
//        }
////        VideoFragment.setVideo(localCurrentItem)
//
//
//        if (fragment != null) {
//            activity.supportFragmentManager.beginTransaction().add(itemId, fragment).commitAllowingStateLoss()
//        }
//    }
//
//    private fun addFragment(fragment: Fragment) {
//        activity.supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commitAllowingStateLoss()
//    }
}

interface VideoLoadListener {
    fun onVideoReceived(video: VideoView)
}