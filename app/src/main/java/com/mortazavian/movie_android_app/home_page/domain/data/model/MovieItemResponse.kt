package com.mortazavian.movie_android_app.home_page.domain.data.model


import com.google.gson.annotations.SerializedName

data class MovieForMainPage(
    @SerializedName("country")
    val country: String,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("imdb_rating")
    val imdbRating: String,
    @SerializedName("poster")
    val poster: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: String
)

data class MovieItemResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("description")
    val description: Description,
    @SerializedName("metadata")
    val metadata: Metadata,
    @SerializedName("status")
    val status: Int
) {
    data class Data(
        @SerializedName("country")
        val country: String,
        @SerializedName("genres")
        val genres: List<String>,
        @SerializedName("id")
        val id: Int,
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("imdb_rating")
        val imdbRating: String,
        @SerializedName("poster")
        val poster: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("year")
        val year: String
    )

    data class Description(
        @SerializedName("en")
        val en: String,
        @SerializedName("fa")
        val fa: String
    )

    data class Metadata(
        @SerializedName("current_page")
        val currentPage: Int,
        @SerializedName("has_next")
        val hasNext: Boolean,
        @SerializedName("has_prev")
        val hasPrev: Boolean,
        @SerializedName("page_count")
        val pageCount: Int,
        @SerializedName("per_page")
        val perPage: Int,
        @SerializedName("total_count")
        val totalCount: Int
    )
}