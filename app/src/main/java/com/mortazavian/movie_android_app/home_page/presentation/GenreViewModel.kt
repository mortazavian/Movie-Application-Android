package com.mortazavian.movie_android_app.home_page.presentation

import androidx.lifecycle.*
import com.mortazavian.movie_android_app.home_page.domain.data.model.Genre
import com.mortazavian.movie_android_app.home_page.domain.data.model.MovieItemResponse
import com.mortazavian.movie_android_app.home_page.domain.data.repository.GenreRepository
import com.mortazavian.movie_android_app.home_page.domain.data.repository.HomeRepository
import com.mortazavian.movie_android_app.shared_component.API
import kotlinx.coroutines.launch

class GenreViewModel (private val repository: GenreRepository) : ViewModel() {

    //region Properties
    private val _genres = MutableLiveData<List<Genre>>()
    val genres: LiveData<List<Genre>> get() = _genres
    //endregion

    fun getAllGenres() {
        viewModelScope.launch {
            val response = repository.getAllGenres()
            if(response.isSuccess){
                response.onSuccess {
                    _genres.postValue(listOf(it))
                }.onFailure {
                    // ToDo
                }
            }
        }
    }
}
class GenreModule {
    companion object {
        val watchRepository: GenreRepository by lazy {
            GenreRepository(API.getAllGenresService)
        }
    }
}

class GenreViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GenreViewModel::class.java)) {
            return GenreViewModel(GenreModule.watchRepository) as T
        }
        throw java.lang.IllegalArgumentException("wrong dependency")
    }
}