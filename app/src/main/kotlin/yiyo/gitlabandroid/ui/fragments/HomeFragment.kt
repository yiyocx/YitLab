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

class HomeFragment(val tabLayout: TabLayout) : Fragment() {

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
        val adapter = ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(AllProjectsFragment(), "All Projects");
        adapter.addFragment(AllProjectsFragment(), "Owned");
        view_pager.setAdapter(adapter);
    }

    fun setupTabLayout() {
        tabLayout.setVisibility(View.VISIBLE)
        tabLayout.addTab(tabLayout.newTab().setText(R.string.projects_all))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.projects_owned))
        tabLayout.setupWithViewPager(view_pager)
    }
}
