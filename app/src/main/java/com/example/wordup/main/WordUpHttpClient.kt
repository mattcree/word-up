package com.example.wordup.main

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface WordUpHttpClient {
    @GET("wordbot")
    fun getWord(): Deferred<Words>
}