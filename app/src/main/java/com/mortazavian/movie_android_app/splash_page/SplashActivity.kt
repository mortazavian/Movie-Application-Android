package com.mortazavian.movie_android_app.splash_page

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mortazavian.movie_android_app.MainActivity
import com.mortazavian.movie_android_app.databinding.ActivitySplashBinding
import com.mortazavian.movie_android_app.register_page.RegisterActivity
import com.mortazavian.movie_android_app.shared_component.data.Constants

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // To remove the status bar.
        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN)
        supportActionBar?.hide()

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("yes", MODE_PRIVATE)

        Log.d("sppp",sharedPreferences.toString())

        // Delay for 4 seconds
        Handler(Looper.getMainLooper()).postDelayed({

            // Start main activity
//            startActivity(Intent(this, MainActivity::class.java))


            // Finish splash activity
            finish()
            val token = sharedPreferences.getBoolean("gooz", false)

            Log.d("token", token.toString())


            Log.d("initialSharedPreference", "Function called")
            Log.d("token-key", "Token retrieved: $token")

//            Intent(this, RegisterActivity::class.java).also {
//                startActivity(it)
//            }

            if (!token) {
                Log.d("initialSharedPreference", "Token is empty, navigating to RegisterFragment")
                Intent(this, RegisterActivity::class.java).also {
                    startActivity(it)
                }
            } else {
                Log.d("initialSharedPreference", "Token found, navigating to HomeFragment")
                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                }

            }
        }, 1000)


    }
}
