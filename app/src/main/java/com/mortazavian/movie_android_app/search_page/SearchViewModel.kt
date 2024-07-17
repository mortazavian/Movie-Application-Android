package com.mortazavian.movie_android_app.search_page

import androidx.lifecycle.*
import com.mortazavian.movie_android_app.home_page.domain.data.repository.HomeRepository
import com.mortazavian.movie_android_app.home_page.presentation.HomeViewModel
import com.mortazavian.movie_android_app.search_page.domain.data.model.MovieSearchResponse
import com.mortazavian.movie_android_app.search_page.domain.data.repository.SearchRepository
import com.mortazavian.movie_android_app.shared_component.API
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: SearchRepository) : ViewModel() {
    //region Properties
    private val _movies = MutableLiveData<List<MovieSearchResponse>>()
    val movies: LiveData<List<MovieSearchResponse>> get() = _movies
    //endregion

    fun getSearchedMovies(searchText: String) {
        viewModelScope.launch {
            val response = repository.searchMovies(searchText)
            if (response.isSuccess) {
                response.onSuccess {
                    _movies.postValue(listOf(it))
                }.onFailure {
                    // ToDo
                }
            }
        }
    }
}

class SearchModule {
    companion object {
        val watchRepository: SearchRepository by lazy {
            SearchRepository(API.searchMovieService)
        }
    }
}

class SearchViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(SearchModule.watchRepository) as T
        }
        throw java.lang.IllegalArgumentException("wrong dependency")
    }
}