package yiyo.gitlabandroid.views.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loginPresenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        loginPresenter.stop();
    }

    @OnClick(R.id.email_sign_in_button)
    public void attemptLogin() {

        // Reset errors
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Get values at the time of the login attempt
        String username = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        loginPresenter.validateCredentials(username, password);
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing In");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void setupUsernameError(String usernameError) {
        mEmailView.setError(usernameError);
    }

    @Override
    public void setupPasswordError() {
        mPasswordView.setError(getString(R.string.error_field_required));
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public Context getContext() {
        return LoginActivity.this;
    }
}
