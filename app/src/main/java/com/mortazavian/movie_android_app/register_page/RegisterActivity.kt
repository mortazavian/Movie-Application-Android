package com.mortazavian.movie_android_app.register_page

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.mortazavian.movie_android_app.MainActivity
import com.mortazavian.movie_android_app.databinding.ActivityRegisterBinding
import com.mortazavian.movie_android_app.register_page.domain.data.model.RegisterMessage
import com.mortazavian.movie_android_app.register_page.domain.data.model.UserInformation
import com.mortazavian.movie_android_app.register_page.presentation.RegisterViewModel
import com.mortazavian.movie_android_app.register_page.presentation.RegisterViewModelFactory
import kotlinx.coroutines.launch
import java.security.MessageDigest

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // To remove the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupButtons()
        observeViewModel()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, RegisterViewModelFactory())[RegisterViewModel::class.java]
    }

    private fun setupButtons() {
        binding.btnRegister.setOnClickListener {
            disableUI()
            val md = MessageDigest.getInstance("MD5")
            val hashBytes = md.digest(binding.etPassword.text.toString().toByteArray())
            val hexStringPassword = hashBytes.joinToString("") { "%02x".format(it) }

            Log.d("hex-string-password", hexStringPassword)

            val userInformation = UserInformation(
                binding.etEmail.text.toString(),
                binding.etName.text.toString(),
                binding.etStudentNumber.text.toString(),
                hexStringPassword
            )
            Log.d("info1", userInformation.toString())

            try {
                lifecycleScope.launch {
                    viewModel.register(userInformation)
                }
            } catch (e: Exception) {
                Log.e("RegisterActivity", "Error registering user: ${e.message}", e)
                Toast.makeText(this@RegisterActivity, "Error registering user", Toast.LENGTH_LONG).show()
                enableUI()
                Log.d("acc","No OK")
            }
        }
    }

    private fun observeViewModel() {
        viewModel.registerResult.observe(this) { result ->
            result?.let {
                if (result.isSuccess) {
                    val registerMessage = result.getOrDefault(null)
                    if (registerMessage != null) {
                        Log.d("12333",registerMessage.status.toString())
                    }
                    if (registerMessage != null && registerMessage.status == 200) {
                        saveUserToken()
                        navigateToMainActivity()
                        Log.d("acc", "OK1")
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Registration Failed: ${registerMessage?.description?.en ?: "Unknown error"}",
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d("acc", "No OK1")
                        enableUI()
                    }
                } else {
                    val error = result.exceptionOrNull()
                    Toast.makeText(
                        this@RegisterActivity,
                        "Error registering user: ${error?.message ?: "Unknown error"}",
                        Toast.LENGTH_LONG
                    ).show()
                    enableUI()
                    Log.e("RegisterActivity", "Error registering user", error)
                }
            }
        }
    }


    private fun disableUI() {
        binding.btnRegister.isEnabled = false
        binding.etEmail.isEnabled = false
        binding.etName.isEnabled = false
        binding.etStudentNumber.isEnabled = false
        binding.etPassword.isEnabled = false
    }

    private fun enableUI() {
        binding.btnRegister.isEnabled = true
        binding.etEmail.isEnabled = true
        binding.etName.isEnabled = true
        binding.etStudentNumber.isEnabled = true
        binding.etPassword.isEnabled = true
    }

    private fun navigateToMainActivity() {
        Intent(this, MainActivity::class.java).also {
            startActivity(it)
            finish() // Optional: Call finish() to prevent going back to the register activity
        }
    }


    private fun saveUserToken() {
        val sharedPreferences = getSharedPreferences("yes", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("gooz", true).apply()
    }
}
