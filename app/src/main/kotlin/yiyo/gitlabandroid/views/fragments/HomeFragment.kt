package yiyo.gitlabandroid.views.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response
import yiyo.gitlabandroid.R
import yiyo.gitlabandroid.model.entities.Project
import yiyo.gitlabandroid.model.rest.ApiService
import yiyo.gitlabandroid.model.rest.RestClient
import yiyo.gitlabandroid.mvp.presenters.HomePresenter
import yiyo.gitlabandroid.mvp.views.HomeView
import kotlin.properties.Delegates
import kotlinx.android.synthetic.fragment_home.*

class HomeFragment : Fragment(), HomeView {

    private val homePresenter by Delegates.lazy { HomePresenter(this@HomeFragment) }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super<Fragment>.onViewCreated(view, savedInstanceState)

        val apiService :ApiService= RestClient.getApiService(getActivity())
        apiService.getProjects(object : Callback<List<Project>> {
            override fun success(t: List<Project>?, response: Response?) {
                println("Bien ${t} Tamaño ${t?.size()}")
            }

            override fun failure(error: RetrofitError?) {
                println("Falle")
            }
        })
    }

    override fun showLoading() {
        projects_progress.setVisibility(View.VISIBLE)
    }

    override fun hideLoading() {
        throw UnsupportedOperationException()
    }

    override fun onStart() {
        super<Fragment>.onStart()
        homePresenter.start()
    }

    override fun onStop() {
        super<Fragment>.onStop()
        homePresenter.stop()
    }

    override fun getContext(): Context = getActivity()
}
