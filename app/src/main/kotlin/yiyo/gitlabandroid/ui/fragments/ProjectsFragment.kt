package yiyo.gitlabandroid.ui.fragments

import android.content.Context
import android.content.Intent
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
import yiyo.gitlabandroid.ui.activities.ProjectDetailActivity
import yiyo.gitlabandroid.ui.adapters.ProjectsAdapter

/**
 * Created by yiyo on 25/08/15.
 */
public class ProjectsFragment(val owned: Boolean = false) : Fragment(), HomeView {

    private val projectsPresenter by lazy(LazyThreadSafetyMode.NONE) { ProjectsPresenter(this@ProjectsFragment, owned) }
    private val projectsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        ProjectsAdapter(getContext(), { project -> projectsPresenter.onProjectClicked(project) })
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_projects, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(getContext())
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL)
        recycler_repositories.setHasFixedSize(true)
        recycler_repositories.setLayoutManager(linearLayoutManager)
        recycler_repositories.setAdapter(projectsAdapter)
    }

    override fun onResume() {
        super.onResume()
        projectsPresenter.onResume()
    }

    override fun onPause() {
        super.onPause()
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

    override fun navigateToProjectDetail(projectId: Int, name: String, pathWithNamespace: String) {
        val intent = Intent(getActivity(), ProjectDetailActivity::class.java)
        intent.putExtra("projectId", projectId)
        intent.putExtra("name", name)
        intent.putExtra("pathWithNamespace", pathWithNamespace)
        startActivity(intent)
    }

    override fun getContext(): Context = getActivity()
}