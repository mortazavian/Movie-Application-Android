package com.mortazavian.movie_android_app.home_page

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBindings
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.mortazavian.movie_android_app.R
import com.mortazavian.movie_android_app.databinding.FragmentHomeBinding
import com.mortazavian.movie_android_app.home_page.domain.data.model.Genre
import com.mortazavian.movie_android_app.home_page.domain.data.model.GenreToShow
import com.mortazavian.movie_android_app.home_page.domain.data.model.MovieForMainPage
import com.mortazavian.movie_android_app.home_page.presentation.GenreViewModel
import com.mortazavian.movie_android_app.home_page.presentation.GenreViewModelFactory
import com.mortazavian.movie_android_app.home_page.presentation.HomeViewModel
import com.mortazavian.movie_android_app.home_page.presentation.HomeViewModelFactory
import com.mortazavian.movie_android_app.home_page.presentation.ui.adapter.GenreAdapter
import com.mortazavian.movie_android_app.home_page.presentation.ui.adapter.MovieAdapter
import kotlinx.coroutines.newFixedThreadPoolContext

class HomeFragment : Fragment() {

    //region properties
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var viewModel2: GenreViewModel
    //endregion

    //region lifecycle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val imageSlider = (R.id.image_slider)

        val slides: List<SlideModel> = listOf(
            SlideModel(R.drawable.her_poster),
            SlideModel(R.drawable.fear_poster),
            SlideModel(R.drawable.starwar_poster),
            SlideModel(R.drawable.lamp_poster),
            SlideModel(R.drawable.moonlight_poster)
            // ... add more slides here
        )
        binding.imageSlider.setImageList(slides, ScaleTypes.CENTER_CROP)



        binding.imageSlider.startLayoutAnimation()

        initialViewModel()
        configViewModel()
        callApi()

        initialViewModel2()
        configViewModel2()
        callApi2()
    }
    //endregion

    //region methods
    private fun initialViewModel() {
        viewModel = ViewModelProvider(this, HomeViewModelFactory())[HomeViewModel::class.java]
    }

    private fun configViewModel() {
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            if (movies.isNotEmpty() && movies[0].data.isNotEmpty()) {
                val lstOfMovies = movies[0].data.map { item ->
                    Log.d("to-work-with", item.toString())
                    MovieForMainPage(
                        genres = item.genres,
                        id = item.id,
                        images = item.images,
                        poster = item.poster,
                        title = item.title,
                        country = item.country,
                        imdbRating = item.imdbRating,
                        year = item.year
                    )
                }

                Log.d("HomeFragment", "Movies list size: ${lstOfMovies.size}")

                // Use requireContext() to get the context
                val adapter = MovieAdapter(requireContext(), lstOfMovies)
                binding.movieRecyclerView.adapter = adapter
                binding.movieRecyclerView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            } else {
                Log.e("HomeFragment", "Movies list is empty or null")
            }
        }
    }

    private fun callApi() {
        viewModel.getAllMovies()
    }

    private fun initialViewModel2() {
        viewModel2 = ViewModelProvider(this, GenreViewModelFactory())[GenreViewModel::class.java]
    }

    private fun configViewModel2() {
        viewModel2.genres.observe(viewLifecycleOwner) { genres ->
            if (genres.isNotEmpty() && genres[0].data.isNotEmpty()) {
                val lstOfGenres = genres[0].data.map { item ->
                    Log.d("to-work-with", item.toString())
                    GenreToShow(
                        id = item.id,
                        name = item.name
                    )
                }

                Log.d("HomeFragment", "Genres list size: ${lstOfGenres.size}")

                val adapter = GenreAdapter(requireContext(), lstOfGenres)
                binding.genreRecyclerView.adapter = adapter
                binding.genreRecyclerView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            } else {
                Log.e("HomeFragment", "Genres list is empty or null")
            }
        }
    }

    private fun callApi2() {
        viewModel2.getAllGenres()
    }
    //endregion
}
