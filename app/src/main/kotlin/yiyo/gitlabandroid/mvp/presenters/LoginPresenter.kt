package yiyo.gitlabandroid.mvp.presenters

import android.text.TextUtils
import android.util.Log

import retrofit.RetrofitError
import rx.functions.Action1
import yiyo.gitlabandroid.R
import yiyo.gitlabandroid.domain.LoginUseCase
import yiyo.gitlabandroid.utils.extension.tag
import yiyo.gitlabandroid.model.entities.Session
import yiyo.gitlabandroid.mvp.views.LoginView
import yiyo.gitlabandroid.utils.Configuration

/**
 * Created by yiyo on 11/07/15.
 */
class LoginPresenter(private val loginView: LoginView) : Presenter {

    override fun start() {
        // Unused
    }

    override fun stop() {
        // Unused
    }

    fun login(username: String, password: String) {
        loginView.showProgress()

        if (validate(username, password)) {
            
            LoginUseCase(username, password, loginView.getContext())
                .execute()
                .subscribe(
                    { session: Session -> onSessionReceived(session) },
                    { error: Throwable -> manageError(error) }
                )
        } else {
            loginView.hideProgress()
        }
    }

    fun validate(username: String, password: String): Boolean {
        var valid = true

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            loginView.setupPasswordError(loginView.getContext().getString(R.string.error_field_required))
            valid = false
        } else {
            loginView.setupPasswordError(null)
        }

        // Check for a valid username address.
        if (TextUtils.isEmpty(username)) {
            loginView.setupUsernameError(loginView.getContext().getString(R.string.error_field_required))
            valid = false
        } else {
            loginView.setupUsernameError(null)
        }

        return valid
    }

    fun onSessionReceived(session: Session) {
        val configuration = Configuration(loginView.getContext())
        val sessionCreated = configuration.createSession(
                session.name, session.username, session.email,
                session.privateToken, session.id)
        loginView.hideProgress()

        if (sessionCreated) {
            loginView.navigateToHome()
            Log.i(tag(), "The user has successfully logged")
        } else {
            Log.e(tag(), "There was a problem creating the session in the SharedPreferences")
        }
    }

    fun manageError(error: Throwable) {
        loginView.hideProgress()
        Log.e(tag(), error.getMessage(), error)

        loginView.showConnectionError(error as RetrofitError)
    }
}
