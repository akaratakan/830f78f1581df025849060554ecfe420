package com.atakanakar.a830f78f1581df025849060554ecfe420.di

import com.atakanakar.a830f78f1581df025849060554ecfe420.BuildConfig
import com.atakanakar.a830f78f1581df025849060554ecfe420.commons.Constants
import com.atakanakar.a830f78f1581df025849060554ecfe420.network.SpaceApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Converter.Factory): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshi)
            .build()

    @Singleton
    @Provides
    fun provideSpaceApi(retrofit: Retrofit): SpaceApi =
        retrofit.create(SpaceApi::class.java)

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideMoshi(): Converter.Factory {
        return MoshiConverterFactory.create(
            Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        )
    }

}