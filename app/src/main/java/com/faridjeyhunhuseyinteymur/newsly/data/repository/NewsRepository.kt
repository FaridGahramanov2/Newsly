package com.faridjeyhunhuseyinteymur.newsly.data.repository

import com.faridjeyhunhuseyinteymur.newsly.data.model.APIResponse
import com.faridjeyhunhuseyinteymur.newsly.data.remote.ApiConfig
import com.faridjeyhunhuseyinteymur.newsly.util.Resource
import retrofit2.HttpException
import java.io.IOException



class NewsRepository {
    suspend fun getNews(category: String? = null): Resource<APIResponse> {
        return try {
            val response = ApiConfig.api.getTopHeadlines(category = category)
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("An error occurred: ${response.message()}")
            }
        } catch (e: HttpException) {
            Resource.Error("Network error: ${e.localizedMessage ?: "Unknown error"}")
        } catch (e: IOException) {
            Resource.Error("Check your internet connection")
        } catch (e: Exception) {
            Resource.Error("An unexpected error occurred")
        }
    }
}