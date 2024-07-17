package com.mortazavian.movie_android_app.home_page.presentation

import androidx.lifecycle.*
import com.mortazavian.movie_android_app.home_page.domain.data.model.MovieItemResponse
import com.mortazavian.movie_android_app.home_page.domain.data.repository.HomeRepository
import com.mortazavian.movie_android_app.shared_component.API
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {

    //region Properties
    private val _movies = MutableLiveData<List<MovieItemResponse>>()
    val movies: LiveData<List<MovieItemResponse>> get() = _movies
    //endregion

    fun getAllMovies() {
        viewModelScope.launch {
            val response = repository.getAllMovies()
            if(response.isSuccess){
                response.onSuccess {
                    _movies.postValue(listOf(it))
                }.onFailure {
                    // ToDo
                }
            }
        }
    }
}

class HomeModule {
    companion object {
        val watchRepository: HomeRepository by lazy {
            HomeRepository(API.baseUserService)
        }
    }
}

class HomeViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(HomeModule.watchRepository) as T
        }
        throw java.lang.IllegalArgumentException("wrong dependency")
    }
}