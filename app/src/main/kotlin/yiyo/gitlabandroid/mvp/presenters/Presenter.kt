package yiyo.gitlabandroid.mvp.presenters

/**
 * Created by yiyo on 11/07/15.

 * Interface that represents a Presenter in the model view presenter Pattern
 * defines methods to manage the Activity / Fragment lifecycle
 */
interface Presenter {

    /**
     * Called when the presenter is initialized
     */
    fun onResume()

    /**
     * Called when the presenter is stop, i.e when an activity
     * or a fragment finishes
     */
    fun onPause()
}
