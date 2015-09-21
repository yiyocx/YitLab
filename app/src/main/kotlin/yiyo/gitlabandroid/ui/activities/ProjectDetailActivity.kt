package yiyo.gitlabandroid.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import kotlinx.android.synthetic.activity_project_detail.tab_layout
import kotlinx.android.synthetic.activity_project_detail.view_pager
import yiyo.gitlabandroid.R
import yiyo.gitlabandroid.ui.adapters.ViewPagerAdapter
import yiyo.gitlabandroid.ui.fragments.ProjectsFragment

/**
 * Created by yiyo on 28/08/15.
 */
class ProjectDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_detail)

        setupToolbar()
        setupViewPager()
        setupTabLayout()
    }

    private fun setupToolbar() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        toolbar.setTitle(getIntent().getStringExtra("name"))
        setSupportActionBar(toolbar)
        val ab = getSupportActionBar()
        ab.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(getSupportFragmentManager())
        adapter.addFragment(ProjectsFragment(owned = true), getString(R.string.project_detail_activity))
        adapter.addFragment(ProjectsFragment(owned = true), getString(R.string.project_detail_code))
        adapter.addFragment(ProjectsFragment(), getString(R.string.project_detail_commits))
        adapter.addFragment(ProjectsFragment(owned = true), getString(R.string.project_detail_issues))
        adapter.addFragment(ProjectsFragment(owned = true), getString(R.string.project_detail_merge_requests))
        adapter.addFragment(ProjectsFragment(owned = true), getString(R.string.project_detail_members))
        view_pager.setAdapter(adapter)
    }

    private fun setupTabLayout() {
        tab_layout.setVisibility(View.VISIBLE)
        tab_layout.setupWithViewPager(view_pager)
    }
}