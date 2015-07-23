package yiyo.gitlabandroid.mvp.views;

import retrofit.RetrofitError;

/**
 * Created by yiyo on 11/07/15.
 */
public interface LoginView extends BaseView {

    void showProgress();

    void hideProgress();

    void setupUsernameError(String usernameError);

    void setupPasswordError(String passwordError);

    void navigateToHome();

    void showConnectionError(RetrofitError error);
}
