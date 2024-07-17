package com.mortazavian.movie_android_app.home_page.domain.data.repository

import android.util.Log
import com.mortazavian.movie_android_app.home_page.domain.data.model.MovieItemResponse
import com.mortazavian.movie_android_app.shared_component.APIService

class HomeRepository(private val api: APIService) {

    suspend fun getAllMovies(): Result<MovieItemResponse> {
        val response = api.getAllPosts()
//        val responseToWorkWith = response.body()?.data

//        Log.d("2323", responseToWorkWith.toString())

        return if (response.isSuccessful) {
            response.body()?.let {
                Result.success(it)
            } ?: Result.failure(Throwable("سرویس خطا داشت"))
        } else {
            Result.failure(Throwable("سرویس انجام نشد"))
        }
    }
}