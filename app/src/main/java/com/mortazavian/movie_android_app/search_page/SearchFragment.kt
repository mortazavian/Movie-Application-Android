package com.mortazavian.movie_android_app.search_page

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mortazavian.movie_android_app.databinding.FragmentHomeBinding
import com.mortazavian.movie_android_app.databinding.FragmentSearchBinding
import com.mortazavian.movie_android_app.home_page.domain.data.model.MovieForMainPage
import com.mortazavian.movie_android_app.home_page.presentation.HomeViewModel
import com.mortazavian.movie_android_app.home_page.presentation.HomeViewModelFactory
import com.mortazavian.movie_android_app.home_page.presentation.ui.adapter.MovieAdapter
import com.mortazavian.movie_android_app.search_page.domain.data.model.MovieSearchPage
import com.mortazavian.movie_android_app.search_page.presentation.ui.adapter.SearchMovieAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    //region properties
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    //endregion

    //region lifecycle
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialViewModel()
        configViewModel()

        // Add TextWatcher to EditText
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No action needed here
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No action needed here
            }

            override fun afterTextChanged(s: Editable?) {
                // Perform search whenever text changes
                callApi()
            }
        })
    }

    //region methods
    private fun initialViewModel() {
        viewModel = ViewModelProvider(this, SearchViewModelFactory())[SearchViewModel::class.java]
    }

    private fun configViewModel() {
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            if (movies.isNotEmpty() && movies[0].data.isNotEmpty()) {
                val lstOfMovies = movies[0].data.map { item ->
                    MovieSearchPage(
                        genres = item.genres,
                        id = item.id,
                        images = item.images,
                        poster = item.poster,
                        title = item.title,
                    )
                }
                val adapter = SearchMovieAdapter(requireContext(), lstOfMovies)
                binding.rvSearchResults.adapter = adapter
                binding.rvSearchResults.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            } else {
                Log.e("SearchFragment", "Movies list is empty or null")
            }
        }
    }

    private fun callApi() {
        val query = binding.etSearch.text.toString()
        if (query.isNotEmpty()) {
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.getSearchedMovies(query)
            }
        }
    }
    //endregion
}


