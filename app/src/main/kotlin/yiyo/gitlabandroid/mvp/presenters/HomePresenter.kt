package yiyo.gitlabandroid.mvp.presenters

import yiyo.gitlabandroid.domain.GetProjectsUseCase
import yiyo.gitlabandroid.model.entities.Project
import yiyo.gitlabandroid.model.rest.RestClient
import yiyo.gitlabandroid.mvp.views.HomeView

/**
 * Created by yiyo on 5/08/15.
 */
public class HomePresenter(private val homeView: HomeView) : Presenter {

    override fun onResume() {
        homeView.showLoading()
        GetProjectsUseCase(RestClient.getApiService(homeView.getContext()))
            .execute()
            .subscribe(
                {projects: List<Project> -> onReceiveProjects(projects)},
                {error: Throwable -> println("Error: $error")},
                { homeView.hideLoading() }
            )
    }

    fun onReceiveProjects(projects: List<Project>) {
        homeView.showProjects(projects)
    }

    override fun onPause() {
        //Unused
    }
}