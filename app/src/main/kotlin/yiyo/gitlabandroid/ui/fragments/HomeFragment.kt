package yiyo.gitlabandroid.ui.fragments

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.fragment_home.view_pager
import yiyo.gitlabandroid.R
import yiyo.gitlabandroid.ui.adapters.ViewPagerAdapter

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater?.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super<Fragment>.onViewCreated(view, savedInstanceState)

        setupViewPager()
        setupTabLayout()
    }

    fun setupViewPager() {
        // The getChildFragmentManager() is Very important! Because fragments inside fragments are
        // not supported with the tipical fragmentManager, it requires NestedFragments and those
        // uses a childFragmentManager(). In other case a strange behaviour occurs
        val adapter = ViewPagerAdapter(getChildFragmentManager())
        adapter.addFragment(ProjectsFragment(), getString(R.string.projects_all))
        adapter.addFragment(ProjectsFragment(owned = true), getString(R.string.projects_owned))
        view_pager.setAdapter(adapter)
    }

    fun setupTabLayout() {
        val tabLayout = getActivity().findViewById(R.id.tab_layout) as TabLayout
        tabLayout.setVisibility(View.VISIBLE)
        tabLayout.setupWithViewPager(view_pager)
    }
}
