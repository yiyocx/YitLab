package yiyo.gitlabandroid.model.rest;

import retrofit.Callback;
import retrofit.http.POST;

/**
 * Created by yiyo on 12/07/15.
 */
public interface ApiService {

    @POST("/session")
    void createSession(Callback callback);
}
