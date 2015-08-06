package yiyo.gitlabandroid.model.rest

import com.google.gson.JsonObject
import retrofit.Callback

import retrofit.http.Body
import retrofit.http.GET
import retrofit.http.POST
import rx.Observable
import yiyo.gitlabandroid.model.entities.Project
import yiyo.gitlabandroid.model.entities.Session

/**
 * Created by yiyo on 12/07/15.
 */
interface ApiService {

    POST("/session")
    fun signIn(Body credentials: JsonObject): Observable<Session>

    GET("/projects")
    fun getProjects(): Observable<List<Project>>
}
