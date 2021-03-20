package com.indiannewssroom.app.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.indiannewssroom.app.ui.fragments.a.FragmentA
import com.indiannewssroom.app.ui.fragments.b.FragmentB
import com.indiannewssroom.app.ui.fragments.c.FragmentC
import com.indiannewssroom.app.ui.fragments.d.FragmentD
import com.indiannewssroom.app.ui.fragments.e.FragmentE
import com.indiannewssroom.app.ui.fragments.f.FragmentF
import com.indiannewssroom.app.ui.fragments.g.FragmentG
import com.indiannewssroom.app.ui.fragments.h.FragmentH
import com.indiannewssroom.app.ui.fragments.home.HomeFragment
import com.indiannewssroom.app.ui.fragments.i.FragmentI

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    val fragments:ArrayList<Fragment> = arrayListOf(
        HomeFragment(),
        FragmentA(),
        FragmentB(),
        FragmentC(),
        FragmentD(),
        FragmentE(),
        FragmentF(),
        FragmentG(),
        FragmentH(),
        FragmentI()
    )
}