package com.mortazavian.movie_android_app.favorite_page

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mortazavian.movie_android_app.databinding.ItemMovieHomeBinding
import com.mortazavian.movie_android_app.detail_page.DetailActivity
import com.mortazavian.movie_android_app.detail_page.domain.data.model.MovieDetails

class FavoriteMoviesAdapter(
    private val context: Context,
    private var items: List<MovieDetails>
) : RecyclerView.Adapter<FavoriteMoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieHomeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]
        holder.bind(data)
    }

    override fun getItemCount() = items.size

    fun updateMovies(newItems: List<MovieDetails>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemMovieHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieDetails) {
            binding.tvMovieTitle.text = item.title
            binding.tvRating.text = item.imdbRating
            binding.tvCountry.text = item.runtime // Assuming runtime for country, you can adjust this
            binding.tvReleaseYear.text = item.released // Assuming released for year, adjust if needed

            Glide.with(itemView)
                .load(item.poster)
                .into(binding.ivMoviePoster)

            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java).apply {
                    putExtra("MOVIE_ID", item.id) // Adjust as necessary for your DetailActivity
                }
                context.startActivity(intent)
            }
        }
    }
}
