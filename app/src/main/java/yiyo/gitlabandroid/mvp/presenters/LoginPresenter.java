package yiyo.gitlabandroid.mvp.presenters;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import yiyo.gitlabandroid.R;
import yiyo.gitlabandroid.mvp.views.LoginView;

/**
 * Created by yiyo on 11/07/15.
 */
public class LoginPresenter implements Presenter<LoginView> {

    private LoginView loginView;

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void attachView(LoginView view) {
        loginView = view;
    }

    public void validateCredentials(String username, String password) {

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            loginView.setupPasswordError();
        }

        // Check for a valid username address.
        if (TextUtils.isEmpty(username)) {
            loginView.setupUsernameError(loginView.getContext().getString(R.string.error_field_required));
        } else if (!isEmailValid(username)) {
            loginView.setupUsernameError(loginView.getContext().getString(R.string.error_invalid_email));
        }
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }
}
