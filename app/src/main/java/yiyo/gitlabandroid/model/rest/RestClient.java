package yiyo.gitlabandroid.model.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by yiyo on 12/07/15.
 */
public class RestClient {

    private static final String BASE_ULR = "https://gitlab.com/api/v3";
    private static ApiService apiService;
    private static RestClient INSTANCE;

    public RestClient() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_ULR)
                .setConverter(new GsonConverter(gson))
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
