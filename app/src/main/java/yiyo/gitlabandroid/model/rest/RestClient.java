package yiyo.gitlabandroid.model.rest;

import retrofit.RestAdapter;

/**
 * Created by yiyo on 12/07/15.
 */
public class RestClient {

    private static final String BASE_ULR = "https://gitlab.com/api/v3";
    private ApiService apiService;

    public RestClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_ULR)
                .build();

        apiService = restAdapter.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }
}
