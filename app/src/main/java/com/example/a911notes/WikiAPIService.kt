package com.example.a911notes
import android.content.Context
import com.example.a911notes.model.Model
import com.readystatesoftware.chuck.ChuckInterceptor
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query


interface WikiApiService {

    @GET("w/api.php")
    fun hitCountCheck(@Query("action") action: String,
                      @Query("format") format: String,
                      @Query("list") list: String,
                      @Query("srsearch") srsearch: String): Observable<Model.Result>

    companion object {
        fun create(context: Context): WikiApiService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder()
                    .addInterceptor(ChuckInterceptor(context))
                    .build())
                .baseUrl("https://en.wikipedia.org/")
                .build()

            return retrofit.create(WikiApiService::class.java)
        }
    }

}