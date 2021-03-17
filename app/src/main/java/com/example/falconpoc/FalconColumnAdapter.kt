package com.example.falconpoc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

class FalconColumnAdapter(private val activity: FragmentActivity, val playerController: PlayerController) :
    RecyclerView.Adapter<FalconColumnAdapter.VideoViewHolder>() {

    private val items = mutableListOf<Video>()

    fun setVideos(videos: List<Video>) {
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
        val videoView: VideoView = v.findViewById(R.id.videoLayout)

        fun bind(video: Video) {
            videoView.loadCard(video, playerController)
        }
    }

    override fun onViewRecycled(holder: VideoViewHolder) {
        super.onViewRecycled(holder)
        holder.videoView.releasePlayer()
    }
}