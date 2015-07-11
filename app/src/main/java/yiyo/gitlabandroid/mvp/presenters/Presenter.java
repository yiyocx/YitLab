package yiyo.gitlabandroid.mvp.presenters;

import android.view.View;

/**
 * Created by yiyo on 11/07/15.
 *
 * Interface that represents a Presenter in the model view presenter Pattern
 * defines methods to manage the Activity / Fragment lifecycle
 */
public interface Presenter<T> {

    /**
     * Called when the presenter is initialized
     */
    void start();

    /**
     * Called when the presenter is stop, i.e when an activity
     * or a fragment finishes
     */
    void stop();

    /**
     * Called for associates the presenter to a view
     * @param view
     */
    void attachView (T view);
}
