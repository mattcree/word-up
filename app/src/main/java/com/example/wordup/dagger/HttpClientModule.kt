package com.example.wordup.dagger

import com.example.wordup.Config
import com.example.wordup.main.WordUpHttpClient
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class HttpClientModule {

    private val connectionPoolMaxIdleConnections = 10
    private val connectionPoolKeepAliveDurationInMinutes = 5L
    private val connectionTimeoutInSeconds = 10L
    private val readTimeoutInSeconds = 60L
    private val writeTimeoutInSeconds = 60L

    @Provides
    @Singleton
    fun providesObjectMapper() = ObjectMapper().registerKotlinModule()

    @Provides
    fun providesHttpClientBuilder() = OkHttpClient.Builder()
        .connectionPool(ConnectionPool(connectionPoolMaxIdleConnections, connectionPoolKeepAliveDurationInMinutes, TimeUnit.MINUTES))
        .connectTimeout(connectionTimeoutInSeconds, TimeUnit.SECONDS)
        .readTimeout(readTimeoutInSeconds, TimeUnit.SECONDS)
        .writeTimeout(writeTimeoutInSeconds, TimeUnit.SECONDS)
        .build()

    @Provides
    fun providesRetrofit(config: Config, client: OkHttpClient, objectMapper: ObjectMapper) = Retrofit.Builder()
        .baseUrl(config.baseUrl)
        .addConverterFactory(JacksonConverterFactory.create(objectMapper))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(client)
        .build()

    @Provides
    fun providesWordUpHttpClient(retrofit: Retrofit): WordUpHttpClient = retrofit.create(WordUpHttpClient::class.java)
}