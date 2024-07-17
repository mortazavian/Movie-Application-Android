//package com.mortazavian.movie_android_app.favorite_page.presentation
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.mortazavian.movie_android_app.detail_page.domain.data.model.MovieDetails
//import com.mortazavian.movie_android_app.favorite_page.domain.repository.FavoriteRepository
//import com.mortazavian.movie_android_app.home_page.domain.data.repository.HomeRepository
//import com.mortazavian.movie_android_app.home_page.presentation.HomeViewModel
//import com.mortazavian.movie_android_app.shared_component.API
//
//class FavoriteViewModel(repository: FavoriteRepository) {
//
//    fun getAllFavorioteMovies(repository: FavoriteRepository): List<MovieDetails> {
//        return repository.getAllFavoriteMovies()
//    }
//}
//
//class FavoriateModule {
//    companion object {
//        val watchRepository: FavoriteRepository by lazy {
//            FavoriteRepository()
//        }
//    }
//}
//
//class FavoriotViewModelFactory : ViewModelProvider.Factory {
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
//            return FavoriteViewModel(FavoriateModule.watchRepository) as T
//        }
//        throw java.lang.IllegalArgumentException("wrong dependency")
//    }
//}