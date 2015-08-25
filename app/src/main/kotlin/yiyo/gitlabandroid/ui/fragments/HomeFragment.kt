package yiyo.gitlabandroid.ui.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.fragment_home.*
import yiyo.gitlabandroid.R
import yiyo.gitlabandroid.model.entities.Project
import yiyo.gitlabandroid.mvp.presenters.HomePresenter
import yiyo.gitlabandroid.mvp.views.HomeView
import yiyo.gitlabandroid.ui.adapters.ProjectsAdapter
import yiyo.gitlabandroid.ui.adapters.ViewPagerAdapter
import kotlin.properties.Delegates

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

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(AllProjectsFragment(), "All Projects");
        adapter.addFragment(AllProjectsFragment(), "Owned");
        view_pager.setAdapter(adapter);
    }

    private fun setupTabLayout() {
        tab_layout.addTab(tab_layout.newTab().setText(R.string.projects_all))
        tab_layout.addTab(tab_layout.newTab().setText(R.string.projects_owned))
        tab_layout.setupWithViewPager(view_pager)
    }
}
