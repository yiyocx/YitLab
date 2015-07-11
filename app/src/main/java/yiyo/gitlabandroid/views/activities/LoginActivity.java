package yiyo.gitlabandroid.views.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yiyo.gitlabandroid.R;
import yiyo.gitlabandroid.mvp.presenters.LoginPresenter;
import yiyo.gitlabandroid.mvp.views.LoginView;


public class LoginActivity extends AppCompatActivity implements LoginView {

    @Bind(R.id.email) AutoCompleteTextView mEmailView;
    @Bind(R.id.password) EditText mPasswordView;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);
    }

    @OnClick(R.id.email_sign_in_button)
    public void attemptLogin() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String username = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        loginPresenter.validateCredentials(username, password);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setupUsernameError(String usernameError) {
        mEmailView.setError(usernameError);
    }

    @Override
    public void setupPasswordError() {
        mPasswordView.setError(getString(R.string.error_invalid_password));
    }

    @Override
    public Context getContext() {
        return LoginActivity.this;
    }
}
