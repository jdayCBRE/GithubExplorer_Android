package com.cbre.jday.githubexplorer_android.api

import com.cbre.jday.githubexplorer_android.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteRepository {


    fun authAPI(): AuthAPI {
        return retrofit().create(AuthAPI::class.java)
    }

    fun githubAPI(): GithubAPI {
        return retrofit().create(GithubAPI::class.java)
    }




    // Private Functions

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient())
                .build()
    }

    private fun okHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor(loggingInterceptor())

//        client.addInterceptor { chain ->
//            val request = chain.request().newBuilder()
//                    .addHeader("Authorization", "Basic {:}").build()
//            chain.proceed(request)
//        }

        return client.build()
    }

    private fun loggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        return logger
    }
}