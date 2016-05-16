package yiyo.gitlabandroid.ui.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.*

/**
 * Created by yiyo on 25/08/15.
 */
public class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    val mFragmentList = ArrayList<Fragment>()
    val mFragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment = mFragmentList.get(position)

    override fun getCount(): Int = mFragmentList.size

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence = mFragmentTitleList.get(position)
}
