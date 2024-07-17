package com.mortazavian.movie_android_app.shared_component

import com.mortazavian.movie_android_app.detail_page.domain.data.model.MovieInformationResponse
import com.mortazavian.movie_android_app.home_page.domain.data.model.Genre
import com.mortazavian.movie_android_app.home_page.domain.data.model.MovieItemResponse
import com.mortazavian.movie_android_app.register_page.domain.data.model.RegisterMessage
import com.mortazavian.movie_android_app.register_page.domain.data.model.UserInformation
import com.mortazavian.movie_android_app.search_page.domain.data.model.MovieSearchResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIService {

    @GET("genres/1/movies")
    suspend fun getAllPosts(): retrofit2.Response<MovieItemResponse>

    @POST("register")
    suspend fun registerUser(@Body userInformation: UserInformation): retrofit2.Response<RegisterMessage>

    @GET("movie/{movieID}")
    suspend fun getMovieDetails(@Path("movieID") movieID: Int): Response<MovieInformationResponse>

    @GET("movies/1/{searchText}")
    suspend fun getSearchMovies(@Path("searchText") searchText: String): Response<MovieSearchResponse>

    @GET("genres")
    suspend fun getAllGenres(): Response<Genre>
}