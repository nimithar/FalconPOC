package com.example.falconpoc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tabLayout)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val videoItems1 = mutableListOf<VideoView>()
        val videoItems2 = mutableListOf<VideoView>()
        val videoItems3 = mutableListOf<VideoView>()

        videoItems1.add(VideoView(
            url = "https://assets.mixkit.co/videos/preview/mixkit-pink-and-blue-ink-1192-large.mp4",
            title = "pink and blue ink",
            description = "this is the description"
        ))

        videoItems1.add(VideoView(
            url = "https://assets.mixkit.co/videos/preview/mixkit-green-ink-1196-large.mp4",
            title = "green ink",
            description = "this is the description"
        ))

        videoItems1.add(VideoView(
            url = "https://assets.mixkit.co/videos/preview/mixkit-portrait-of-a-woman-in-a-pool-1259-large.mp4",
            title = "woman in pool",
            description = "this is the description"
        ))

        videoItems2.add(VideoView(
            url = "https://assets.mixkit.co/videos/preview/mixkit-yellow-and-orange-ink-1198-small.mp4",
            title = "yellow ink",
            description = "this is the description"
        ))

        videoItems2.add(VideoView(
            url = "https://assets.mixkit.co/videos/preview/mixkit-purple-and-white-ink-1203-large.mp4",
            title = "purple ink",
            description = "this is the description"
        ))

        videoItems2.add(VideoView(
            url = "https://assets.mixkit.co/videos/preview/mixkit-waves-in-the-water-1164-large.mp4",
            title = "waves in water",
            description = "this is the description"
        ))

        videoItems3.add(VideoView(
            url = "https://assets.mixkit.co/videos/preview/mixkit-tree-with-yellow-flowers-1173-large.mp4",
            title = "tree with yellow flowers",
            description = "this is the description"
        ))

        videoItems3.add(VideoView(
            url = "https://assets.mixkit.co/videos/preview/mixkit-red-frog-on-a-log-1487-large.mp4",
            title = "red frog on a log",
            description = "this is the description"
        ))

        videoItems3.add(VideoView(
            url = "https://assets.mixkit.co/videos/preview/mixkit-small-pink-flowers-1186-large.mp4",
            title = "small pink flowers",
            description = "this is the description"
        ))

        videoItems3.add(VideoView(
            url = "https://assets.mixkit.co/videos/preview/mixkit-palm-tree-in-front-of-the-sun-1191-large.mp4",
            title = "palm tree in front of the sun",
            description = "this is the description"
        ))

        val map = mutableMapOf<String, List<VideoView>>()
        map["Cat 1"] = videoItems1
        map["Cat 2"] = videoItems2
        map["Cat 3"] = videoItems3

        val adapter = ViewPagerAdapter(this)
        adapter.setItems(map)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getTabHeaderAt(position)
        }.attach()
    }

}