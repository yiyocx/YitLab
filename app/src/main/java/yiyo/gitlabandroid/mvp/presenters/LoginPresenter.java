package yiyo.gitlabandroid.mvp.presenters;

import android.text.TextUtils;
import android.util.Log;

import retrofit.RetrofitError;
import rx.Subscription;
import yiyo.gitlabandroid.R;
import yiyo.gitlabandroid.domain.LoginUsecase;
import yiyo.gitlabandroid.model.rest.models.Session;
import yiyo.gitlabandroid.mvp.views.LoginView;

/**
 * Created by yiyo on 11/07/15.
 */
public class LoginPresenter implements Presenter<LoginView> {

    private LoginView loginView;
    private Subscription mSessionSubscription;

    public LoginPresenter() {

    }

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

    public void login(String username, String password) {
        loginView.showProgress();

        if (validate(username, password)) {
            mSessionSubscription = new LoginUsecase(username, password).execute().subscribe(
                    this::onSessionReceived,
                    this::manageError
            );
        } else {
            loginView.hideProgress();
        }
    }

    public boolean validate(String username, String password) {
        boolean valid = true;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            loginView.setupPasswordError(loginView.getContext().getString(R.string.error_field_required));
            valid = false;
        } else {
            loginView.setupPasswordError(null);
        }

        // Check for a valid username address.
        if (TextUtils.isEmpty(username)) {
            loginView.setupUsernameError(loginView.getContext().getString(R.string.error_field_required));
            valid = false;
        } else {
            loginView.setupUsernameError(null);
        }

        return valid;
    }

    public void onSessionReceived(Session session) {
        loginView.hideProgress();
        loginView.navigateToHome();
    }

    public void manageError(Throwable error) {
        loginView.hideProgress();
        Log.e("LoginError", error.getMessage(), error);

        loginView.showConnectionError((RetrofitError) error);
    }
}
