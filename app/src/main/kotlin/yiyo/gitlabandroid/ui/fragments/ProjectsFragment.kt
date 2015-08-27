package yiyo.gitlabandroid.ui.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.fragment_projects.projects_progress
import kotlinx.android.synthetic.fragment_projects.recycler_repositories
import yiyo.gitlabandroid.R
import yiyo.gitlabandroid.model.entities.Project
import yiyo.gitlabandroid.mvp.presenters.ProjectsPresenter
import yiyo.gitlabandroid.mvp.views.HomeView
import yiyo.gitlabandroid.ui.adapters.ProjectsAdapter
import kotlin.properties.Delegates

/**
 * Created by yiyo on 25/08/15.
 */
public class ProjectsFragment(val owned: Boolean = false) : Fragment(), HomeView {

    private val projectsPresenter by Delegates.lazy { ProjectsPresenter(this@ProjectsFragment, owned) }
    private val projectsAdapter by Delegates.lazy { ProjectsAdapter(getContext()) }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_projects, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super<Fragment>.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(getContext())
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL)
        recycler_repositories.setHasFixedSize(true)
        recycler_repositories.setLayoutManager(linearLayoutManager)
        recycler_repositories.setAdapter(projectsAdapter)
    }

    override fun onResume() {
        super<Fragment>.onResume()
        projectsPresenter.onResume()
    }

    override fun onPause() {
        super<Fragment>.onPause()
        projectsPresenter.onPause()
    }

    override fun showLoading() {
        projects_progress.setVisibility(View.VISIBLE)
    }

    override fun hideLoading() {
        projects_progress.setVisibility(View.GONE)
    }

    override fun showProjects(projectsReceived: List<Project>) {
        projectsAdapter.projects = projectsReceived
    }

    override fun getContext(): Context = getActivity()
}