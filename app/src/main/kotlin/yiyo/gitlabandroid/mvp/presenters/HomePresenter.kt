package yiyo.gitlabandroid.mvp.presenters

import yiyo.gitlabandroid.mvp.views.HomeView

/**
 * Created by yiyo on 5/08/15.
 */
public class HomePresenter(private val homeView: HomeView) : Presenter {

    override fun start() {
        homeView.showLoading()
    }

    override fun stop() {
        //Unused
    }
}