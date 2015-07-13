package yiyo.gitlabandroid.mvp.presenters;

import android.text.TextUtils;

import yiyo.gitlabandroid.R;
import yiyo.gitlabandroid.domain.LoginUsecaseController;
import yiyo.gitlabandroid.mvp.views.LoginView;

/**
 * Created by yiyo on 11/07/15.
 */
public class LoginPresenter implements Presenter<LoginView> {

    private LoginView loginView;

    @Override
    public void start() {
        // Unused
    }

    @Override
    public void stop() {
        // Unused
    }

    @Override
    public void attachView(LoginView view) {
        loginView = view;
    }

    public void validateCredentials(String username, String password) {
        loginView.showProgress();

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            loginView.setupPasswordError();
            loginView.hideProgress();
        }

        // Check for a valid username address.
        if (TextUtils.isEmpty(username)) {
            loginView.setupUsernameError(loginView.getContext().getString(R.string.error_field_required));
            loginView.hideProgress();
        } else if (isEmail(username)) {
            loginView.hideProgress();
        } else {
            new LoginUsecaseController(username, password).execute();
            loginView.hideProgress();
        }


    }

    private boolean isEmail(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }
}
