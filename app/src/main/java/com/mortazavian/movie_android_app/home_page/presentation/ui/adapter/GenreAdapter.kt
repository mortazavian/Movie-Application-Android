package com.mortazavian.movie_android_app.home_page.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mortazavian.movie_android_app.databinding.ItemGenreBinding
import com.mortazavian.movie_android_app.home_page.domain.data.model.GenreToShow

class GenreAdapter(
    private val context: Context,
    private val genres: List<GenreToShow>
) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    inner class GenreViewHolder(val binding: ItemGenreBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding = ItemGenreBinding.inflate(LayoutInflater.from(context), parent, false)
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = genres[position]
        holder.binding.genreName.text = genre.name
    }

    override fun getItemCount(): Int {
        return genres.size
    }
}
