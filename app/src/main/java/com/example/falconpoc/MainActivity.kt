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
        val videoItems1 = mutableListOf<Video>()
        val videoItems2 = mutableListOf<Video>()
        val videoItems3 = mutableListOf<Video>()

        videoItems1.add(Video(
            id = "1",
            url = "https://assets.mixkit.co/videos/preview/mixkit-pink-and-blue-ink-1192-large.mp4",
            title = "pink and blue ink",
            description = "this is the description"
        ))

        videoItems1.add(Video(
            id = "2",
            url = "https://assets.mixkit.co/videos/preview/mixkit-green-ink-1196-large.mp4",
            title = "green ink",
            description = "this is the description"
        ))

        videoItems1.add(Video(
            id = "3",
            url = "https://assets.mixkit.co/videos/preview/mixkit-portrait-of-a-woman-in-a-pool-1259-large.mp4",
            title = "woman in pool",
            description = "this is the description"
        ))

        videoItems1.add(Video(
            id = "4",
            url = "https://assets.mixkit.co/videos/preview/mixkit-cold-looking-fashion-woman-in-a-winter-environment-39879-large.mp4",
            title = "Cold-looking fashion woman in a winter environment",
            description = "this is the description"
        ))

        videoItems1.add(Video(
            id = "5",
            url = "https://assets.mixkit.co/videos/preview/mixkit-father-and-his-little-daughter-eating-marshmallows-in-nature-39765-large.mp4",
            title = "Father and his little daughter eating marshmallows in nature",
            description = "this is the description"
        ))

        videoItems1.add(Video(
            id = "6",
            url = "https://assets.mixkit.co/videos/preview/mixkit-conceptual-image-of-a-man-with-glasses-and-hair-with-32646-large.mp4",
            title = "Conceptual image of a man with glasses and hair with sky clouds",
            description = "this is the description"
        ))

        videoItems2.add(Video(
            id = "7",
            url = "https://assets.mixkit.co/videos/preview/mixkit-yellow-and-orange-ink-1198-small.mp4",
            title = "yellow ink",
            description = "this is the description"
        ))

        videoItems2.add(Video(
            id = "8",
            url = "https://assets.mixkit.co/videos/preview/mixkit-purple-and-white-ink-1203-large.mp4",
            title = "purple ink",
            description = "this is the description"
        ))

        videoItems2.add(Video(
            id = "9",
            url = "https://assets.mixkit.co/videos/preview/mixkit-waves-in-the-water-1164-large.mp4",
            title = "waves in water",
            description = "this is the description"
        ))

        videoItems3.add(Video(
            id = "10",
            url = "https://assets.mixkit.co/videos/preview/mixkit-tree-with-yellow-flowers-1173-large.mp4",
            title = "tree with yellow flowers",
            description = "this is the description"
        ))

        videoItems3.add(Video(
            id = "11",
            url = "https://assets.mixkit.co/videos/preview/mixkit-red-frog-on-a-log-1487-large.mp4",
            title = "red frog on a log",
            description = "this is the description"
        ))

        videoItems3.add(Video(
            id = "12",
            url = "https://assets.mixkit.co/videos/preview/mixkit-small-pink-flowers-1186-large.mp4",
            title = "small pink flowers",
            description = "this is the description"
        ))

        videoItems3.add(Video(
            id = "13",
            url = "https://assets.mixkit.co/videos/preview/mixkit-palm-tree-in-front-of-the-sun-1191-large.mp4",
            title = "palm tree in front of the sun",
            description = "this is the description"
        ))

        val map = mutableMapOf<String, List<Video>>()
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