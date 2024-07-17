//package com.mortazavian.movie_android_app.favorite_page.domain.repository
//
//import android.content.Context
//import androidx.core.content.ContentProviderCompat.requireContext
//import com.google.gson.Gson
//import com.mortazavian.movie_android_app.detail_page.domain.data.model.MovieDetails
//
//class FavoriteRepository {
//
//    fun getAllFavoriteMovies(): List<MovieDetails> {
//        val sharedPreferences = requireContext().getSharedPreferences("MovieAppPrefs", Context.MODE_PRIVATE)
//        val favoritesSet = sharedPreferences.getStringSet("favorites", emptySet()) ?: emptySet()
//        val gson = Gson()
//        var favoriteMovies = favoritesSet.map { gson.fromJson(it, MovieDetails::class.java) }
//        favoriteMovies = favoriteMovies.reversed()
//
//        var favMovies = return favoriteMovies
//    }
//
//}