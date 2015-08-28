package yiyo.gitlabandroid.mvp.presenters

import android.widget.Toast
import yiyo.gitlabandroid.domain.GetProjectsUseCase
import yiyo.gitlabandroid.model.entities.Project
import yiyo.gitlabandroid.model.rest.RestClient
import yiyo.gitlabandroid.mvp.views.HomeView

/**
 * Created by yiyo on 5/08/15.
 */
public class ProjectsPresenter(private val homeView: HomeView, private val owned: Boolean = false) : Presenter {

    override fun onResume() {
        homeView.showLoading()

        if (owned) {
            GetProjectsUseCase(RestClient.getApiService(homeView.getContext()))
                    .ownedProjects()
                    .subscribe(
                            { projects: List<Project> -> onReceiveProjects(projects) },
                            { error: Throwable -> println("Error: $error") },
                            { homeView.hideLoading() }
                    )
        } else {
            GetProjectsUseCase(RestClient.getApiService(homeView.getContext()))
                    .execute()
                    .subscribe(
                            { projects: List<Project> -> onReceiveProjects(projects) },
                            { error: Throwable -> println("Error: $error") },
                            { homeView.hideLoading() }
                    )
        }
    }

    fun onProjectClicked(project: Project) {
        homeView.navigateToProjectDetail(project.id)
    }

    fun onReceiveProjects(projects: List<Project>) {
        homeView.showProjects(projects)
    }

    override fun onPause() {
        //Unused
    }
}