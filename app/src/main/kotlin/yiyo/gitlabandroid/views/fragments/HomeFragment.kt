package yiyo.gitlabandroid.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import retrofit.Callback
import retrofit.RetrofitError
import retrofit.client.Response
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers

import yiyo.gitlabandroid.R
import yiyo.gitlabandroid.model.entities.Project
import yiyo.gitlabandroid.model.entities.Session
import yiyo.gitlabandroid.model.rest.ApiService
import yiyo.gitlabandroid.model.rest.RestClient

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
}
