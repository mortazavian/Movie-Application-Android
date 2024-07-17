package com.mortazavian.movie_android_app.register_page.domain.data.repository

import android.annotation.SuppressLint
import android.util.Log
import com.google.android.material.internal.ContextUtils.getActivity
import com.mortazavian.movie_android_app.MainActivity
import com.mortazavian.movie_android_app.register_page.domain.data.model.RegisterMessage
import com.mortazavian.movie_android_app.register_page.domain.data.model.UserInformation
import com.mortazavian.movie_android_app.shared_component.APIService


class RegisterRepository(private val api: APIService) {

    @SuppressLint("RestrictedApi")
    suspend fun register(userInformation: UserInformation): Result<RegisterMessage> {
        Log.d("info3", userInformation.toString())

        val response = api.registerUser(userInformation)

        Log.d("info4", userInformation.toString())
        Log.e("1111", response.body().toString())

        return if (response.isSuccessful) {
            Log.d("info5",response.toString())
//            mainActivity.userCreated()

            response.body()?.let {
//                navigateToHomeFragment()  // Call the function to navigate to the HomeFragment
                Result.success(it)
            } ?: Result.failure(Throwable("سرویس خطا داشت"))
        } else {
            Result.failure(Throwable("سرویس انجام نشد"))
        }
    }

//    private fun navigateToHomeFragment() {
//        val transaction = fragmentManager.beginTransaction()
//        transaction.replace(R.id.container, HomeFragment())  // Replace with your actual HomeFragment
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }
}
