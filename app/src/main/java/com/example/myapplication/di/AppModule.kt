package com.example.myapplication.di

import com.example.myapplication.common.Constants
import com.example.myapplication.data.remote.api.MovieApi
import com.example.myapplication.data.repository.MovieRepositoryImpl
import com.example.myapplication.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi{
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(Interceptor { chain ->
            var request = chain.request()
            val url = request.url.newBuilder().addQueryParameter("api_key",Constants.API_KEY).build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        })

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieApi) : MovieRepository{
        return MovieRepositoryImpl(api)
    }
}