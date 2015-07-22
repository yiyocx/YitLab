package yiyo.gitlabandroid.domain;

import com.google.gson.JsonObject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import yiyo.gitlabandroid.model.rest.ApiService;
import yiyo.gitlabandroid.model.rest.RestClient;
import yiyo.gitlabandroid.model.rest.models.Session;

/**
 * Created by yiyo on 12/07/15.
 */
public class LoginUsecase implements Usecase<Session> {

    private String mUsername;
    private String mPassword;

    public LoginUsecase(String username, String password) {
        this.mUsername = username;
        this.mPassword = password;
    }

    public Observable<Session> login() {
        ApiService apiService = RestClient.getApiService();
        JsonObject credentials = new JsonObject();

        if (isEmail(mUsername)) {
            credentials.addProperty("email", mUsername);
        } else {
            credentials.addProperty("login", mUsername);
        }
        credentials.addProperty("password", mPassword);

        return apiService.signIn(credentials)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private boolean isEmail(String email) {
        return email.contains("@");
    }

    @Override
    public Observable<Session> execute() {
        return login();
    }
}
