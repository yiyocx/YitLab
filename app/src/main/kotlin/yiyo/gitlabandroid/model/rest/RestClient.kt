package yiyo.gitlabandroid.model.rest

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.okhttp.OkHttpClient
import retrofit.RequestInterceptor

import retrofit.RestAdapter
import retrofit.client.OkClient
import retrofit.converter.GsonConverter
import yiyo.gitlabandroid.utils.Configuration

/**
 * Created by yiyo on 12/07/15.
 */
class RestClient {

    companion object {
        val BASE_ULR = "https://gitlab.com/api/v3"

        fun getApiService(context: Context): ApiService {
            val requestInterceptor = RequestInterceptor() {
                it ->
                    val configuration = Configuration(context);
                    it.addHeader("PRIVATE-TOKEN", configuration.getPrivateToken())
                    it.addHeader("Content-Type", "application/json")
            }

            val gson = GsonBuilder().setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'").create()
            val restAdapter = RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint(BASE_ULR)
                    .setRequestInterceptor(requestInterceptor)
                    .setClient(OkClient())
                    .setConverter(GsonConverter(gson))
                    .build()

            return restAdapter.create(javaClass<ApiService>())
        }
    }
}
