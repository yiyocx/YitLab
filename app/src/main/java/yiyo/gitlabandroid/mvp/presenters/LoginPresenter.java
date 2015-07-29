package yiyo.gitlabandroid.mvp.presenters;

import android.text.TextUtils;
import android.util.Log;

import retrofit.RetrofitError;
import rx.functions.Action1;
import yiyo.gitlabandroid.R;
import yiyo.gitlabandroid.domain.LoginUsecase;
import yiyo.gitlabandroid.model.rest.models.Session;
import yiyo.gitlabandroid.mvp.views.LoginView;
import yiyo.gitlabandroid.utils.Configuration;

/**
 * Created by yiyo on 11/07/15.
 */
public class LoginPresenter implements Presenter<LoginView> {
    private static final String TAG = LoginPresenter.class.getSimpleName();

    private LoginView loginView;

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
            new LoginUsecase(username, password).execute().subscribe(
                    new Action1<Session>() {
                        @Override
                        public void call(Session session) {
                            LoginPresenter.this.onSessionReceived(session);
                        }
                    },
                    new Action1<Throwable>() {
                        @Override
                        public void call(Throwable error) {
                            LoginPresenter.this.manageError(error);
                        }
                    }
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
        Configuration configuration = new Configuration(loginView.getContext());
        boolean sessionCreated = configuration.createSession(session.getName(), session.getUsername(), session.getEmail(),
                session.getPrivateToken(), session.getId());
        loginView.hideProgress();

        if (sessionCreated) {
            loginView.navigateToHome();
            Log.i(TAG, "The user has successfully logged");
        } else {
            Log.e(TAG, "There was a problem creating the session in the SharedPreferences");
        }
    }

    public void manageError(Throwable error) {
        loginView.hideProgress();
        Log.e(TAG, error.getMessage(), error);

        loginView.showConnectionError((RetrofitError) error);
    }
}
