package com.example.falconpoc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(private val activity: FragmentActivity) :
    RecyclerView.Adapter<ViewPagerAdapter.VideoCategoriesViewHolder>() {
    private val items = mutableListOf<List<VideoView>>()
    private val categoriesMap = mutableMapOf<Int, String>()

    fun setItems(map: Map<String, List<VideoView>>) {
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
        private val recyclerView: RecyclerView = v.findViewById(R.id.falconColumnRecyclerView)
        private val adapter = FalconColumnAdapter(activity)

        fun bind(videos: List<VideoView>) {
            adapter.setVideos(videos)
            recyclerView.adapter = adapter
        }
    }
}