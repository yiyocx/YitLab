package yiyo.gitlabandroid.domain;

import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
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
    public void login() {
        ApiService apiService = RestClient.getApiService();
        JsonObject credentials = new JsonObject();

        if (isEmail(mUsername)) {
            credentials.addProperty("email", mUsername);
        } else {
            credentials.addProperty("login", mUsername);
        }
        credentials.addProperty("password", mPassword);

        apiService.signIn(credentials, new Callback<JsonObject>() {
            @Override
            public void success(JsonObject jsonObject, Response response) {
                System.out.println("La respuesta=" + jsonObject);
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("Algo salio mal");
            }
        });
    }

    @Override
    public void execute() {
        login();
    }

    private boolean isEmail(String email) {
        return email.contains("@");
    }
}
