package com.mortazavian.movie_android_app.search_page.presentation.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mortazavian.movie_android_app.R
import com.mortazavian.movie_android_app.databinding.ItemMovieBinding
import com.mortazavian.movie_android_app.detail_page.DetailActivity
import com.mortazavian.movie_android_app.search_page.domain.data.model.MovieSearchPage

class SearchMovieAdapter(private val context: Context, private var movies: List<MovieSearchPage>) :
    RecyclerView.Adapter<SearchMovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(context), parent, false)
        var animation: Animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)

        binding.root.animation = animation
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateMovies(newMovies: List<MovieSearchPage>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieSearchPage) {
            binding.movieTitle.text = movie.title
            binding.rateingSearch.text = "4"
            binding.countrySearch.text = "USA"
            binding.dateSearch.text = "2003"
            Glide.with(context)
                .load(movie.poster)
                .placeholder(R.drawable.dog_day_afternoon_poster)
                .into(binding.moviePoster)

            binding.movieGenres.text = movie.genres.joinToString(", ")

            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java).apply {
                    putExtra("MOVIE_ID", movie.id ) // Adjust as necessary for your DetailActivity
                }
                context.startActivity(intent)
            }
        }


    }
}
