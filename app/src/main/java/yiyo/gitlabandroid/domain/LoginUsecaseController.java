package yiyo.gitlabandroid.domain;

import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import yiyo.gitlabandroid.model.rest.ApiService;
import yiyo.gitlabandroid.model.rest.RestClient;

/**
 * Created by yiyo on 12/07/15.
 */
public class LoginUsecaseController implements LoginUsecase {

    private String mUsername;
    private String mPassword;

    public LoginUsecaseController(String username, String password) {
        this.mUsername = username;
        this.mPassword = password;
    }

    @Override
    public Observable<JsonObject> login() {
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
    public Observable<JsonObject> execute() {
        return login();
    }
}
