package yiyo.gitlabandroid.views.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.net.HttpURLConnection;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.RetrofitError;
import yiyo.gitlabandroid.R;
import yiyo.gitlabandroid.mvp.presenters.LoginPresenter;
import yiyo.gitlabandroid.mvp.views.LoginView;
import yiyo.gitlabandroid.utils.Configuration;


public class LoginActivity extends AppCompatActivity implements LoginView {

    @Bind(R.id.email_text_input_layout) TextInputLayout emailInputLayout;
    @Bind(R.id.password_text_input_layout) TextInputLayout passwordInputLayout;
    @Bind(R.id.sign_in_button) Button signInButton;
    private LoginPresenter loginPresenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Configuration configuration = new Configuration(LoginActivity.this);

        if (configuration.isLoggedIn()) {
            // El usuario ya esta logueado. Se redirige a la Actividad Principal
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

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

    @OnClick(R.id.sign_in_button)
    public void attemptLogin() {
        // Get values at the time of the login attempt
        String username = emailInputLayout.getEditText().getText().toString();
        String password = passwordInputLayout.getEditText().getText().toString();

        loginPresenter.login(username, password);
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("Signing In");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void setupUsernameError(String usernameError) {
        emailInputLayout.setError(usernameError);
    }

    @Override
    public void setupPasswordError(String passwordError) {
        passwordInputLayout.setError(passwordError);
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showConnectionError(RetrofitError error) {
        AlertDialog.Builder alertBuilder;

        if (error.getKind() == RetrofitError.Kind.NETWORK) {

            // Cuando no hay una conexión establecida
            alertBuilder = new AlertDialog.Builder(LoginActivity.this)
                    .setTitle(getString(R.string.error_no_connection_title))
                    .setMessage(getString(R.string.error_no_connection_description))
                    .setPositiveButton(getString(R.string.action_retry), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            LoginActivity.this.attemptLogin();
                        }
                    })
                    .setNegativeButton(getString(R.string.action_cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setCancelable(false);
            alertBuilder.show();
        } else if (error.getResponse().getStatus() == HttpURLConnection.HTTP_UNAUTHORIZED) {

            alertBuilder = new AlertDialog.Builder(LoginActivity.this)
                    .setTitle(getString(R.string.error_sign_in_failed_title))
                    .setMessage(getString(R.string.error_sign_in_failed_description))
                    .setPositiveButton(getString(R.string.action_accept), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                        }
                    });
            alertBuilder.show();
        } else if (error.getKind() == RetrofitError.Kind.UNEXPECTED) {

            alertBuilder = new AlertDialog.Builder(LoginActivity.this)
                    .setTitle(getString(R.string.error_unexpected_title))
                    .setMessage(getString(R.string.error_unexpected_description))
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                        }
                    });
            alertBuilder.show();
        }
    }

    @Override
    public Context getContext() {
        return LoginActivity.this;
    }
}
