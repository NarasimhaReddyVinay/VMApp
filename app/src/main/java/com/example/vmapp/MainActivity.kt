package com.example.vmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.vmapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.ViewPager
        viewPager.adapter = viewPagerAdapter
        val tabs: TabLayout = binding.tabLayout
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_people_alt_24)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_meeting_room_24)
    }
}