package yiyo.gitlabandroid.model.rest;

import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by yiyo on 12/07/15.
 */
public interface ApiService {

    @POST("/session")
    void signIn(@Body JsonObject credentials, Callback<JsonObject> callback);
}
