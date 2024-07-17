package com.mortazavian.movie_android_app.detail_page

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.system.Os
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.mortazavian.movie_android_app.MainActivity
import com.mortazavian.movie_android_app.R
import com.mortazavian.movie_android_app.databinding.ActivityDetailBinding
import com.mortazavian.movie_android_app.detail_page.domain.data.model.MovieDetails
import com.mortazavian.movie_android_app.detail_page.domain.presentation.ui.adapter.MovieImagesAdapter
import com.mortazavian.movie_android_app.detail_page.domain.presentation.DetailViewModel
import com.mortazavian.movie_android_app.detail_page.domain.presentation.DetailViewModelFactory
import com.mortazavian.movie_android_app.home_page.presentation.ui.adapter.MovieAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private lateinit var sharedPreferences: SharedPreferences

    private var movieDetails: MovieDetails? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // To remove the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MovieAppPrefs", Context.MODE_PRIVATE)

        // Retrieve the MOVIE_ID from the intent extras
        val movieId = intent.getIntExtra("MOVIE_ID", -1)
        initialButtons()
        initialViewModel()
        configViewModel()
        callApi(movieId = movieId)
    }

    private fun initialViewModel() {
        viewModel = ViewModelProvider(this, DetailViewModelFactory())[DetailViewModel::class.java]
    }

    private fun configViewModel() {
        viewModel.movie.observe(this) { movie ->
            val movieData = movie.data
            movieDetails = MovieDetails(
                title = movieData.title,
                imdbRating = movieData.imdbRating,
                runtime = movieData.runtime,
                released = movieData.released,
                plot = movieData.plot,
                actors = movieData.actors,
                poster = movieData.poster,
                images = movieData.images,
                id = movieData.id
            )

            binding.tvMovieTitle.text = movieDetails?.title
            binding.tvRating.text = movieDetails?.imdbRating
            binding.tvDuration.text = movieDetails?.runtime
            binding.tvReleaseDate.text = movieDetails?.released
            binding.tvSummary.text = movieDetails?.plot
            binding.tvActors.text = movieDetails?.actors

            Glide.with(this@DetailActivity)
                .load(movieDetails?.poster)
                .into(binding.ivMoviePoster)

            val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            binding.ivMoviePoster.animation = animation

            val adapter = MovieImagesAdapter(this, movieDetails?.images ?: emptyList())
            binding.movieImagesRecyclerView.adapter = adapter
            binding.movieImagesRecyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            // Check if the movie is in favorites and update the icon
            movieDetails?.id?.let { movieId ->
                if (isMovieFavorite(movieId)) {
                    binding.ivFavorite.setColorFilter(resources.getColor(R.color.red)) // Set to red
                } else {
                    binding.ivFavorite.setColorFilter(resources.getColor(android.R.color.transparent)) // Set to transparent
                }
            }
        }


    }

    private fun callApi(movieId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getMovieInfromation(movieId)
        }
    }

    fun initialButtons() {
        binding.ivBack.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }
        binding.ivFavorite.setOnClickListener {
            movieDetails?.let {
                if (isMovieFavorite(it.id)) {
                    removeMovieFromFavorites(it)
                    binding.ivFavorite.setColorFilter(resources.getColor(android.R.color.transparent)) // Set to transparent
                    Log.d("fav-icon", "fav icon clicked and movie removed from favorites")
                } else {
                    saveMovieToFavorites(it)
                    binding.ivFavorite.setColorFilter(resources.getColor(R.color.red)) // Set to red
                    Log.d("fav-icon", "fav icon clicked and movie saved to favorites")
                }
            } ?: run {
                Log.d("fav-icon", "fav icon clicked but movie details are null")
            }
        }
    }


    private fun saveMovieToFavorites(movie: MovieDetails) {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val movieJson = gson.toJson(movie)

        // Retrieve the current set of favorite movies
        val favoritesSet =
            sharedPreferences.getStringSet("favorites", mutableSetOf())?.toMutableSet()
                ?: mutableSetOf()

        // Add the new favorite movie JSON to the set
        favoritesSet.add(movieJson)

        // Save the updated set back to SharedPreferences
        editor.putStringSet("favorites", favoritesSet)
        editor.apply()
    }

    private fun isMovieFavorite(movieId: Int): Boolean {
        val favoritesSet =
            sharedPreferences.getStringSet("favorites", mutableSetOf()) ?: return false
        for (movieJson in favoritesSet) {
            val movie = Gson().fromJson(movieJson, MovieDetails::class.java)
            if (movie.id == movieId) {
                return true
            }
        }
        return false
    }

    private fun removeMovieFromFavorites(movie: MovieDetails) {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val movieJson = gson.toJson(movie)

        // Retrieve the current set of favorite movies
        val favoritesSet =
            sharedPreferences.getStringSet("favorites", mutableSetOf())?.toMutableSet()
                ?: mutableSetOf()

        // Remove the movie JSON from the set
        favoritesSet.remove(movieJson)

        // Save the updated set back to SharedPreferences
        editor.putStringSet("favorites", favoritesSet)
        editor.apply()
    }

}