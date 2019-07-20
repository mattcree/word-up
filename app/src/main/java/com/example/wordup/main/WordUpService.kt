package com.example.wordup.main

import retrofit2.HttpException
import javax.inject.Inject

class WordUpService @Inject constructor(private val httpClient: WordUpHttpClient) {

    suspend fun getWord() = try {
        httpClient.getWord().await().words.first()
    } catch (e: HttpException) {
        throw WordUpServiceException()
    }
}