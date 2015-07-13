package yiyo.gitlabandroid.domain;

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
    }

    @Override
    public void execute() {
        login();
    }
}
