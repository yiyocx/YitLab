package yiyo.gitlabandroid.domain;

import rx.Observable;

/**
 * Created by yiyo on 12/07/15.
 */
public interface Usecase<T> {

    Observable<T> execute();
}
