package yiyo.gitlabandroid.domain

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import yiyo.gitlabandroid.model.entities.Project
import yiyo.gitlabandroid.model.rest.ApiService

/**
 * Created by yiyo on 6/08/15.
 */
class GetProjectsUseCase(private val apiService: ApiService) : UseCase<List<Project>> {

    override fun execute(): Observable<List<Project>> {
        return apiService.getProjects()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
    }
}