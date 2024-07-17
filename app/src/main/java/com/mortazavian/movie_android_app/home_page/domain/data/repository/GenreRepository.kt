package com.mortazavian.movie_android_app.home_page.domain.data.repository

import com.mortazavian.movie_android_app.home_page.domain.data.model.Genre
import com.mortazavian.movie_android_app.shared_component.APIService

class GenreRepository(private val api: APIService) {
    suspend fun getAllGenres(): Result<Genre> {

        val response = api.getAllGenres()

        return if (response.isSuccessful) {
            response.body()?.let {
                Result.success(it)
            } ?: Result.failure(Throwable("سرویس خطا داشت"))
        } else {
            Result.failure(Throwable("سرویس انجام نشد"))
        }
    }
}