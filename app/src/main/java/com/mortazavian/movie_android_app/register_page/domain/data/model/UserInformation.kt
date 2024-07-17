package com.mortazavian.movie_android_app.register_page.domain.data.model


import com.google.gson.annotations.SerializedName

data class UserInformation(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("studentNumber")
    val studentNumber: String,
    @SerializedName("password")
    val password: String

)