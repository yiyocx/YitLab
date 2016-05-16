package yiyo.gitlabandroid.mvp.views

/**
 * Created by yiyo on 11/07/15.
 */
interface LoginView : BaseView {

    fun showProgress()

    fun hideProgress()

    fun setupUsernameError(usernameError: String?)

    fun setupPasswordError(passwordError: String?)

    fun navigateToHome()

//    fun showConnectionError(error: RetrofitError)
}
