package yiyo.gitlabandroid.ui.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import yiyo.gitlabandroid.R
import yiyo.gitlabandroid.mvp.presenters.HomePresenter
import yiyo.gitlabandroid.mvp.views.HomeView
import kotlin.properties.Delegates
import kotlinx.android.synthetic.fragment_home.*
import yiyo.gitlabandroid.model.entities.Project
import android.support.v7.widget.RecyclerView
import android.widget.ArrayAdapter
import yiyo.gitlabandroid.ui.adapters.ProjectsAdapter

class HomeFragment : Fragment(), HomeView {

    private val homePresenter by Delegates.lazy { HomePresenter(this@HomeFragment) }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater?.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super<Fragment>.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(getContext())
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL)
        recycler_repositories.setHasFixedSize(true)
        recycler_repositories.setLayoutManager(linearLayoutManager)
    }

    override fun onResume() {
        super<Fragment>.onResume()
        homePresenter.onResume()
    }

    override fun onPause() {
        super<Fragment>.onPause()
        homePresenter.onPause()
    }

    override fun showLoading() {
        projects_progress.setVisibility(View.VISIBLE)
    }

    override fun hideLoading() {
        projects_progress.setVisibility(View.GONE)
    }

    override fun showProjects(projects: List<Project>) {
        val projectsAdapter = ProjectsAdapter(projects, getContext())
        recycler_repositories.setAdapter(projectsAdapter)
    }

    override fun getContext(): Context = getActivity()
}
