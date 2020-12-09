package ru.skillbranch.skillarticles.repository.http.client

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.skillbranch.skillarticles.BuildConfig.BASE_URL
import ru.skillbranch.skillarticles.repository.http.DeliveryApi


object DeliveryRetrofitProvider {
    const val BEARER = "Bearer"
    const val REFRESH_TOKEN =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVmN2M2OWNjODIyMDk1MDAzYzZkZDI3YiIsImlhdCI6MTYwNjM0MTc5OH0.-udWT__SrFAGpOV8jxbchWIgjWDWRij9zKoy398gX9Y"

    fun createRetrofit(): DeliveryApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()
            .create(DeliveryApi::class.java)
    }
}