package com.mortazavian.movie_android_app

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.mortazavian.movie_android_app.databinding.ActivityMainBinding
import com.mortazavian.movie_android_app.favorite_page.FarvoriteFragment
import com.mortazavian.movie_android_app.home_page.HomeFragment

import com.mortazavian.movie_android_app.search_page.SearchFragment
import com.mortazavian.movie_android_app.shared_component.data.Constants


class MainActivity : AppCompatActivity() {

    //region properties
    lateinit var binding: ActivityMainBinding
//    private lateinit var sharedPreferences: SharedPreferences
    //endregion

    //region lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // To remove the status bar.
        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN)
        supportActionBar?.hide()

        supportFragmentManager.beginTransaction()
            .replace(binding.mainContainer.id, HomeFragment())
            .addToBackStack(null)
            .commit()

        initialButtons()
//        initialSharedPreference()


    }
    //endregion

    //region methods
//    private fun initialSharedPreference() {
//        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_KEY, MODE_PRIVATE)
//        val token = sharedPreferences.getString(Constants.USER_TOKEN_KEY, "").toString()
//
//        Log.d("initialSharedPreference", "Function called")
//        Log.d("token-key", "Token retrieved: $token")
//
//        if (token.isEmpty()) {
//            Log.d("initialSharedPreference", "Token is empty, navigating to RegisterFragment")
//            supportFragmentManager.beginTransaction()
//                .replace(binding.mainContainer.id, RegisterFragment())
//                .addToBackStack(null)
//                .commit()
//        } else if (token.isNotEmpty()) {
//            Log.d("initialSharedPreference", "Token found, navigating to HomeFragment")
//            supportFragmentManager.beginTransaction()
//                .replace(binding.mainContainer.id, HomeFragment())
//                .addToBackStack(null)
//                .commit()
//        }
//    }


    @SuppressLint("ResourceAsColor")
    private fun initialButtons() {
        binding.home.setOnClickListener {
            binding.home.setBackgroundColor(R.color.red)
            binding.search.setBackgroundColor(R.color.white)
            binding.favorite.setBackgroundColor(R.color.white)
            supportFragmentManager.beginTransaction()
                .replace(binding.mainContainer.id, HomeFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.search.setOnClickListener {
            binding.search.setBackgroundColor(R.color.red)
            binding.home.setBackgroundColor(R.color.white)
            binding.favorite.setBackgroundColor(R.color.white)
            supportFragmentManager.beginTransaction()
                .replace(binding.mainContainer.id, SearchFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.favorite.setOnClickListener {
            binding.favorite.setBackgroundColor(R.color.red)
            binding.search.setBackgroundColor(R.color.white)
            binding.home.setBackgroundColor(R.color.white)
            supportFragmentManager.beginTransaction()
                .replace(binding.mainContainer.id, FarvoriteFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    //endregion
}
