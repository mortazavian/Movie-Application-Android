package com.mortazavian.movie_android_app.detail_page.domain.data.repository

import com.mortazavian.movie_android_app.detail_page.domain.data.model.MovieInformationResponse
import com.mortazavian.movie_android_app.home_page.domain.data.model.MovieItemResponse
import com.mortazavian.movie_android_app.shared_component.API
import com.mortazavian.movie_android_app.shared_component.APIService

class DetailRepository(private val api: APIService) {

    suspend fun getMovieInformation(id : Int): Result<MovieInformationResponse> {
        val response = api.getMovieDetails(id)

        return if (response.isSuccessful) {
            response.body()?.let {
                Result.success(it)
            } ?: Result.failure(Throwable("سرویس خطا داشت"))
        } else {
            Result.failure(Throwable("سرویس انجام نشد"))
        }
    }
}