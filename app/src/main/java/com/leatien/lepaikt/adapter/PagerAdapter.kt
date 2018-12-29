package com.leatien.lepaikt.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class PagerAdapter(fm: FragmentManager, private val fragments: List<Fragment>) : FragmentPagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment {
       return fragments[p0]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}