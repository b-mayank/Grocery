package com.example.grocery.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.grocery.fragments.ProductListFragment
import com.example.grocery.models.SubCategory


class MyFragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var mFragment: ArrayList<Fragment> = ArrayList()
    var mTitle: ArrayList<String> = ArrayList()

    override fun getCount(): Int {
        return mTitle.size
    }

    override fun getItem(position: Int): Fragment {
        return mFragment[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitle[position]
    }

    fun addFragment(subCategory: SubCategory) {
        mFragment.add(ProductListFragment.newInstance(subCategory.subId))
        mTitle.add(subCategory.subName)
    }
}