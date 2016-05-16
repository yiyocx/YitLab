package yiyo.gitlabandroid.model.rest

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import yiyo.gitlabandroid.utils.Configuration

/**
 * Created by yiyo
 */
class RestClient {

    companion object {
        val BASE_ULR = "https://gitlab.com/api/v3/"

        fun getApiService(context: Context): ApiService {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor { chain ->
                val configuration = Configuration(context);
                val originalRequest = chain.request()
                val request = originalRequest.newBuilder()
                        .addHeader("PRIVATE-TOKEN", configuration.getPrivateToken())
                        .addHeader("Content-Type", "application/json")
                .method(originalRequest.method(), originalRequest.body())
                .build()

                chain.proceed(request)
            }
            val client = httpClient.build()
            val gson = GsonBuilder().setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'").create()

            val restAdapter = Retrofit.Builder()
                    .baseUrl(BASE_ULR)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build()

            return restAdapter.create(ApiService::class.java)
        }
    }
}
