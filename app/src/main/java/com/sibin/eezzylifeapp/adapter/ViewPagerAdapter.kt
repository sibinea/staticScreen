package com.sibin.eezzylifeapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sibin.eezzylifeapp.view.CalenderWeekFragment


class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    var selectedPos: Int = -1
    override fun createFragment(position: Int): Fragment {
        return CalenderWeekFragment.newInstance(position, selectedPos)
    }

    override fun getItemCount(): Int {
        return CARD_ITEM_SIZE
    }


    companion object {
        private const val CARD_ITEM_SIZE = 10

    }
}