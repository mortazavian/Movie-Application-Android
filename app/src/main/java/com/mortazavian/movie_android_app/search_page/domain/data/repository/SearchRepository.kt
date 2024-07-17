package com.mortazavian.movie_android_app.search_page.domain.data.repository

import com.mortazavian.movie_android_app.detail_page.domain.data.model.MovieInformationResponse
import com.mortazavian.movie_android_app.search_page.domain.data.model.MovieSearchResponse
import com.mortazavian.movie_android_app.shared_component.APIService

class SearchRepository(private val api: APIService) {
    suspend fun searchMovies(searchText: String): Result<MovieSearchResponse> {
        val response = api.getSearchMovies(searchText)

        return if (response.isSuccessful) {
            response.body()?.let {
                Result.success(it)
            } ?: Result.failure(Throwable("سرویس خطا داشت"))
        } else {
            Result.failure(Throwable("سرویس انجام نشد"))
        }
    }
}