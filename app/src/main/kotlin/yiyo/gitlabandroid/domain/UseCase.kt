package yiyo.gitlabandroid.domain

import rx.Observable

/**
 * Created by yiyo on 12/07/15.
 */
interface UseCase<T> {

    fun execute(): Observable<T>
}
