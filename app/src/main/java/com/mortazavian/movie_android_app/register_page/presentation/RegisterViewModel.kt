package com.mortazavian.movie_android_app.register_page.presentation

import android.util.Log
import androidx.lifecycle.*
import com.mortazavian.movie_android_app.register_page.domain.data.model.RegisterMessage
import com.mortazavian.movie_android_app.register_page.domain.data.model.UserInformation
import com.mortazavian.movie_android_app.register_page.domain.data.repository.RegisterRepository
import com.mortazavian.movie_android_app.shared_component.API
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: RegisterRepository
) : ViewModel() {

    private val _registerResult = MutableLiveData<Result<RegisterMessage>>()
    val registerResult: LiveData<Result<RegisterMessage>> get() = _registerResult

    fun register(userInformation: UserInformation) {
        viewModelScope.launch {
            Log.d("info2", userInformation.toString())
            val response = repository.register(userInformation)
            _registerResult.value = response
        }
    }
}

class RegisterModule {
    companion object {
        val watchRepository: RegisterRepository by lazy {
            RegisterRepository(API.registerService)
        }
    }
}

class RegisterViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(
                RegisterModule.watchRepository,
            ) as T
        }
        throw IllegalArgumentException("wrong dependency")
    }
}
