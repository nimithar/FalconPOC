package com.example.falconpoc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

class FalconColumnAdapter(private val activity: FragmentActivity) :
    RecyclerView.Adapter<FalconColumnAdapter.VideoViewHolder>() {
    private val items = mutableListOf<VideoView>()
    var currentItem: VideoView? = null

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
        fun bind(video: VideoView) {
            currentItem = video
        }
    }

    override fun onViewAttachedToWindow(holder: VideoViewHolder) {
        attachFragmentToContainer(R.id.fragmentContainer)

        super.onViewAttachedToWindow(holder)
    }

    private fun attachFragmentToContainer(itemId: Int) {
        val localCurrentItem = currentItem ?: return
        val fragment = if (activity.supportFragmentManager.fragments.firstOrNull() { it is VideoFragment } == null) {
            VideoFragment(localCurrentItem)
        } else {
            null
        }
        if (fragment != null) {
            activity.supportFragmentManager.beginTransaction().add(itemId, fragment).commitAllowingStateLoss()
        }
    }
}

interface VideoLoadListener {
    fun onVideoReceived(video: VideoView)
}