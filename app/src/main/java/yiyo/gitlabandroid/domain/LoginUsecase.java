package yiyo.gitlabandroid.domain;

import com.google.gson.JsonObject;

import rx.Observable;

/**
 * Created by yiyo on 12/07/15.
 */
public interface LoginUsecase extends Usecase<JsonObject> {

    Observable<JsonObject> login();
}
