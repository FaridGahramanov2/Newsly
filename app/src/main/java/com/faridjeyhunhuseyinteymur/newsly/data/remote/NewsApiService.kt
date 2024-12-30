package com.faridjeyhunhuseyinteymur.newsly.data.remote

import com.faridjeyhunhuseyinteymur.newsly.data.model.APIResponse
import com.faridjeyhunhuseyinteymur.newsly.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("category") category: String? = null,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<APIResponse>
}