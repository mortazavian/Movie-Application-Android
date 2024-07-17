package com.mortazavian.movie_android_app.favorite_page

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.mortazavian.movie_android_app.databinding.FragmentFarvoriteBinding
import com.mortazavian.movie_android_app.detail_page.domain.data.model.MovieDetails

import com.mortazavian.movie_android_app.home_page.presentation.GenreViewModel
import com.mortazavian.movie_android_app.home_page.presentation.GenreViewModelFactory

class FarvoriteFragment() : Fragment() {

    // region properties
    private lateinit var binding: FragmentFarvoriteBinding
    private lateinit var favoriteMoviesAdapter: FavoriteMoviesAdapter
//    private lateinit var viewModel: FavoriteViewModel
    // endregion

    // region lifecycle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFarvoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadFavoriteMovies()
        initialViewModel()
    }

    private fun initialViewModel() {
//        viewModel = ViewModelProvider(this, FavoriotViewModelFactory())[FavoriteViewModel::class.java]
    }
    // endregion

    // region methods
    private fun setupRecyclerView() {
        favoriteMoviesAdapter = FavoriteMoviesAdapter(requireContext(), emptyList())
        binding.recyclerViewFavorites.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteMoviesAdapter
        }
    }

    private fun loadFavoriteMovies() {
        val sharedPreferences = requireContext().getSharedPreferences("MovieAppPrefs", Context.MODE_PRIVATE)
        val favoritesSet = sharedPreferences.getStringSet("favorites", emptySet()) ?: emptySet()

        val gson = Gson()
        var favoriteMovies = favoritesSet.map { gson.fromJson(it, MovieDetails::class.java) }

        val lst = mutableListOf<MovieDetails>()

        for (item in favoritesSet) {
            val tempMovie = gson.fromJson(item, MovieDetails::class.java)
            lst.add(tempMovie)
        }

        // Reverse the list of favorite movies
        favoriteMovies = lst

        // Update your adapter with the reversed list of favorite movies
        favoriteMoviesAdapter.updateMovies(favoriteMovies)

//        val favMovies = viewModel.getAllFavorioteMovies(repository = FavoriteRepository())

    }
    // endregion
}
