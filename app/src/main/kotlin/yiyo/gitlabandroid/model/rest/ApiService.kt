package yiyo.gitlabandroid.model.rest

import com.google.gson.JsonObject
import retrofit.http.Body
import retrofit.http.GET
import retrofit.http.POST
import retrofit.http.Path
import rx.Observable
import yiyo.gitlabandroid.model.entities.Event
import yiyo.gitlabandroid.model.entities.Project
import yiyo.gitlabandroid.model.entities.Session

/**
 * Created by yiyo on 12/07/15.
 */
interface ApiService {

    @POST("/session")
    fun signIn(@Body credentials: JsonObject): Observable<Session>

    @GET("/projects")
    fun getAllProjects(): Observable<List<Project>>

    @GET("/projects/owned")
    fun getOwnedProjects(): Observable<List<Project>>

    @GET("/projects/{id}/events")
    fun getProjectEvents(@Path("id") id: Int): Observable<List<Event>>
}
