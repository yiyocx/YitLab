package yiyo.gitlabandroid.model.rest;

import retrofit.RestAdapter;

/**
 * Created by yiyo on 12/07/15.
 */
public class RestClient {

    private static final String BASE_ULR = "https://gitlab.com/api/v3";
    private static ApiService apiService;
    private static RestClient INSTANCE;

    public RestClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_ULR)
                .build();

        apiService = restAdapter.create(ApiService.class);
    }

    public static ApiService getApiService() {
        if (INSTANCE == null) {
            INSTANCE = new RestClient();
        }
        return apiService;
    }
}
