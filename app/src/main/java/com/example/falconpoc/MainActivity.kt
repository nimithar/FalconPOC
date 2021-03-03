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

        val map = mutableMapOf<String, List<VideoView>>()
        map["Cat 1"] = videoItems1
        map["Cat 2"] = videoItems2

        val adapter = ViewPagerAdapter(this)
        adapter.setItems(map)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getTabHeaderAt(position)
        }.attach()
    }

}