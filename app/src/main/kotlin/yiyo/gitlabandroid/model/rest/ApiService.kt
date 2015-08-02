package yiyo.gitlabandroid.model.rest

import com.google.gson.JsonObject

import retrofit.http.Body
import retrofit.http.POST
import rx.Observable
import yiyo.gitlabandroid.model.rest.models.Session

/**
 * Created by yiyo on 12/07/15.
 */
interface ApiService {

    POST("/session")
    fun signIn(Body credentials: JsonObject): Observable<Session>
}
