package yiyo.gitlabandroid.model.rest

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import retrofit.RestAdapter
import retrofit.converter.GsonConverter

/**
 * Created by yiyo on 12/07/15.
 */
class RestClient {

    companion object {
        val BASE_ULR = "https://gitlab.com/api/v3"

        val apiService: ApiService
            get() {
                val gson = GsonBuilder().setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'").create()
                val restAdapter = RestAdapter.Builder()
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .setEndpoint(BASE_ULR)
                        .setConverter(GsonConverter(gson))
                        .build()

                return restAdapter.create(javaClass<ApiService>())
            }
    }
}
