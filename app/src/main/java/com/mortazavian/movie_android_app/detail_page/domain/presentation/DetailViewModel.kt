package com.mortazavian.movie_android_app.detail_page.domain.presentation

import androidx.lifecycle.*
import com.mortazavian.movie_android_app.detail_page.domain.data.model.MovieInformationResponse
import com.mortazavian.movie_android_app.detail_page.domain.data.repository.DetailRepository
import com.mortazavian.movie_android_app.home_page.domain.data.model.MovieItemResponse
import com.mortazavian.movie_android_app.home_page.domain.data.repository.HomeRepository
import com.mortazavian.movie_android_app.home_page.presentation.HomeViewModel
import com.mortazavian.movie_android_app.shared_component.API
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: DetailRepository) : ViewModel() {
    //region Properties
    private val _movie = MutableLiveData<MovieInformationResponse>()
    val movie: LiveData<MovieInformationResponse> get() = _movie
    //endregion

    fun getMovieInfromation(id: Int) {
        viewModelScope.launch {
            val response = repository.getMovieInformation(id)
            if (response.isSuccess) {
                response.onSuccess {
                    _movie.postValue((it))
                }.onFailure {
                    // ToDo
                }
            }
        }
    }
}

class DetailModule {
    companion object {
        val watchRepository: DetailRepository by lazy {
            DetailRepository(API.Companion.getMovieInfromationService)
        }
    }
}

class DetailViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(DetailModule.watchRepository) as T
        }
        throw java.lang.IllegalArgumentException("wrong dependency")
    }
}