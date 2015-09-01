package yiyo.gitlabandroid.mvp.views

import yiyo.gitlabandroid.model.entities.Project

/**
 * Created by yiyo on 5/08/15.
 */
interface HomeView : BaseView {

    fun showLoading()

    fun hideLoading()

    fun showProjects(projectsReceived: List<Project>)

    fun navigateToProjectDetail(projectId: Int, name: String, pathWithNamespace: String)
}