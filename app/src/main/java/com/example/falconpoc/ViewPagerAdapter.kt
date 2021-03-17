package com.example.falconpoc

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class ViewPagerAdapter(private val activity: FragmentActivity) :
    RecyclerView.Adapter<ViewPagerAdapter.VideoCategoriesViewHolder>() {
    private val items = mutableListOf<List<Video>>()
    private val categoriesMap = mutableMapOf<Int, String>()

    fun setItems(map: Map<String, List<Video>>) {
        items.clear()
        categoriesMap.clear()
        var i = 0
        map.forEach {
            items.add(it.value)
            categoriesMap[i] = it.key
            i++
        }
        notifyDataSetChanged()
    }

    fun getTabHeaderAt(position: Int): String {
        if (position < categoriesMap.size) {
            return categoriesMap[position].orEmpty()
        }
        return ""
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoCategoriesViewHolder {
        return VideoCategoriesViewHolder(
            LayoutInflater.from(activity).inflate(R.layout.view_pager_column, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: VideoCategoriesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class VideoCategoriesViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val columnViewPager: ViewPager2 = v.findViewById(R.id.falconColumnViewPager)
        private val playerController = PlayerController(activity.applicationContext)
        private val adapter = FalconColumnAdapter(activity, playerController)

        fun bind(videos: List<Video>) {
            adapter.setVideos(videos)
            columnViewPager.adapter = adapter
            columnViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
//                    TODO("Not yet implemented")
                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
//                    TODO("Not yet implemented")
                }

                override fun onPageSelected(position: Int) {
                    Log.v("TAG", "onPageSelected")
                    val video = videos[position]
//                    playerController.pausePreviousVideo()
                    playerController.play()
                }

            })
        }

    }
}