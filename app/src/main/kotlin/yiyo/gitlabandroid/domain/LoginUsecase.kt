package yiyo.gitlabandroid.domain

import android.content.Context
import com.google.gson.JsonObject
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import yiyo.gitlabandroid.model.rest.RestClient
import yiyo.gitlabandroid.model.entities.Session

/**
 * Created by yiyo on 12/07/15.
 */
class LoginUsecase(val username: String, val password: String, val context: Context) : Usecase<Session> {

    fun login(): Observable<Session> {
        val apiService = RestClient.getApiService(context)
        val credentials = JsonObject()

        if (isEmail(username)) {
            credentials.addProperty("email", username)
        } else {
            credentials.addProperty("login", username)
        }
        credentials.addProperty("password", password)

        return apiService.signIn(credentials)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun isEmail(email: String) = email.contains("@")

    override fun execute(): Observable<Session> = login()
}
