package com.example.devicecatalog.di

import android.content.Context
import com.example.devicecatalog.BuildConfig
import com.example.devicecatalog.api.ApiService
import com.example.devicecatalog.repository.Repository
import com.example.devicecatalog.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        // Create a cache directory and set cache size
        val cacheDir = File(context.cacheDir, "http_cache")
        val cache = Cache(cacheDir, 10L * 1024L * 1024L) // 10 MB cache size

        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(logging)
            }

            // Add the CacheInterceptor to manage cache control
            addInterceptor(CacheInterceptor())

            // Set cache for the client
            cache(cache)

            // Set timeouts as before
            connectTimeout(5, TimeUnit.MINUTES)
            readTimeout(5, TimeUnit.MINUTES)
            writeTimeout(5, TimeUnit.MINUTES)
            callTimeout(5, TimeUnit.MINUTES)
        }.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): ApiService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesRepository(repositoryImpl: RepositoryImpl): Repository = repositoryImpl

    class CacheInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
//            val originalResponse = chain.proceed(request)
//
//            val shouldUseCache = request.header(CACHE_CONTROL_HEADER) != CACHE_CONTROL_NO_CACHE
//            if (!shouldUseCache) return originalResponse
//
//            val cacheControl = CacheControl.Builder()
//                .maxAge(5, TimeUnit.MINUTES)
//                .build()
//
//            return originalResponse.newBuilder()
//                .header(CACHE_CONTROL_HEADER, cacheControl.toString())
//                .build()

            ////
            val response = chain.proceed(chain.request())

            // Control caching behavior
            return response.newBuilder()
                .header(
                    "Cache-Control",
                    "public, max-age=" + TimeUnit.MINUTES.toSeconds(5)
                ) // 5 minutes cache duration
                .build()
        }
    }
}