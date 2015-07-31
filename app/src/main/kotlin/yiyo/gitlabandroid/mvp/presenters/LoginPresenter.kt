package yiyo.gitlabandroid.mvp.presenters

import android.text.TextUtils
import android.util.Log

import retrofit.RetrofitError
import rx.functions.Action1
import yiyo.gitlabandroid.R
import yiyo.gitlabandroid.domain.LoginUsecase
import yiyo.gitlabandroid.model.rest.models.Session
import yiyo.gitlabandroid.mvp.views.LoginView
import yiyo.gitlabandroid.utils.Configuration

/**
 * Created by yiyo on 11/07/15.
 */
class LoginPresenter(val loginView: LoginView) : Presenter {

    private val TAG = javaClass<LoginPresenter>().getSimpleName()

    override fun start() {
        // Unused
    }

    override fun stop() {
        // Unused
    }

    fun login(username: String, password: String) {
        loginView.showProgress()

        if (validate(username, password)) {
            LoginUsecase(username, password).execute()
                .subscribe(object : Action1<Session> {
                    override fun call(session: Session) {
                        onSessionReceived(session)
                    }
                }, object : Action1<Throwable> {
                    override fun call(error: Throwable) {
                        manageError(error)
                    }
                })
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
                session.getName(), session.getUsername(), session.getEmail(),
                session.getPrivateToken(), session.getId())
        loginView.hideProgress()

        if (sessionCreated) {
            loginView.navigateToHome()
            Log.i(TAG, "The user has successfully logged")
        } else {
            Log.e(TAG, "There was a problem creating the session in the SharedPreferences")
        }
    }

    fun manageError(error: Throwable) {
        loginView.hideProgress()
        Log.e(TAG, error.getMessage(), error)

        loginView.showConnectionError(error as RetrofitError)
    }
}
