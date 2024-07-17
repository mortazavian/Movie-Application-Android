package com.mortazavian.movie_android_app.home_page.domain.data.model


import com.google.gson.annotations.SerializedName

data class GenreToShow(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

data class Genre(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("description")
    val description: Description,
    @SerializedName("status")
    val status: Int
) {
    data class Data(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )

    data class Description(
        @SerializedName("en")
        val en: String,
        @SerializedName("fa")
        val fa: String
    )
}