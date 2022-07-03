package com.example.vmapp

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.vmapp.view.PeopleFragment
import com.example.vmapp.view.RoomsFragment

class ViewPagerAdapter(private val context: Context, fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 2
    }
    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> PeopleFragment()
            1 -> RoomsFragment()
            else -> {
                PeopleFragment()
            }
        }
    }
}