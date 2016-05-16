package yiyo.gitlabandroid.model.rest

import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import rx.Observable
import yiyo.gitlabandroid.model.entities.Event
import yiyo.gitlabandroid.model.entities.Project
import yiyo.gitlabandroid.model.entities.Session

/**
 * Created by yiyo on 12/07/15.
 */
interface ApiService {

    @POST("session")
    fun signIn(@Body credentials: JsonObject): Observable<Session>

    @GET("projects")
    fun getAllProjects(): Observable<List<Project>>

    @GET("projects/owned")
    fun getOwnedProjects(): Observable<List<Project>>

    @GET("projects/{id}/events")
    fun getProjectEvents(@Path("id") id: Int): Observable<List<Event>>
}
