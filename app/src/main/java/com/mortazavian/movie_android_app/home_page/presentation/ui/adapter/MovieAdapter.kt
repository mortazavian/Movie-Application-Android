package com.mortazavian.movie_android_app.home_page.presentation.ui.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mortazavian.movie_android_app.MainActivity
import com.mortazavian.movie_android_app.R
import com.mortazavian.movie_android_app.databinding.ItemMovieHomeBinding
import com.mortazavian.movie_android_app.detail_page.DetailActivity
import com.mortazavian.movie_android_app.home_page.domain.data.model.MovieForMainPage

class MovieAdapter(
    private val context: Context,
    private val items: List<MovieForMainPage>
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieHomeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        var animation: Animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)

        binding.root.animation = animation
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = items[position]
        holder.bind(data)
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(private val binding: ItemMovieHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieForMainPage) {
            Log.d("23234", item.toString())
            binding.tvMovieTitle.text = item.title
            binding.tvRating.text = item.imdbRating
            binding.tvCountry.text = item.country
            binding.tvReleaseYear.text = item.year

//            binding.ivMoviePoster.animation = animation

            Glide.with(itemView)
                .load(item.poster)  // item.poster should be a URL or file path
                .into(binding.ivMoviePoster)

            itemView.setOnClickListener {
                Log.d("click", item.id.toString())

                // Create an intent to start DetailActivity
                val intent = Intent(context, DetailActivity::class.java).apply {
                    putExtra("MOVIE_ID", item.id)  // Pass the movie ID to the detail activity
                }
                context.startActivity(intent)
            }
        }
    }
}
//class MovieAdapter(
//    private val items: List<MovieForMainPage>
//) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding = ItemMovieHomeBinding.inflate(
//            LayoutInflater.from(parent.context),
//            parent,
//            false
//        )
//        return ViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val data = items[position]
//
//        holder.bind(data)
//    }
//
//    override fun getItemCount() = items.size
//
//    inner class ViewHolder(private val binding: ItemMovieHomeBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(item: MovieForMainPage) {
//
//            Log.d("23234", item.toString())
//            binding.tvMovieTitle.text = item.title
////            binding.ivMoviePoster.setImageResource(item.poster.hashCode())
//            binding.tvRating.text = item.imdbRating
//            binding.tvCountry.text = item.country
//            binding.tvReleaseYear.text = item.year
//
//            Glide.with(itemView)
//                .load(item.poster)  // item.poster should be a URL or file path
//                .into(binding.ivMoviePoster);
//
//
//            Glide.with(itemView)
//                .load(item.poster)  // item.poster should be a URL or file path
//                .into(binding.ivMoviePoster);
//
////            binding.ivMoviePoster.setImageResource(item.poster)
//
//        }
//    }
//}
