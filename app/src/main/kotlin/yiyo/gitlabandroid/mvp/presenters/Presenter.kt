package yiyo.gitlabandroid.mvp.presenters

import android.view.View

/**
 * Created by yiyo on 11/07/15.

 * Interface that represents a Presenter in the model view presenter Pattern
 * defines methods to manage the Activity / Fragment lifecycle
 */
interface Presenter {

    /**
     * Called when the presenter is initialized
     */
    fun start()

    /**
     * Called when the presenter is stop, i.e when an activity
     * or a fragment finishes
     */
    fun stop()
}