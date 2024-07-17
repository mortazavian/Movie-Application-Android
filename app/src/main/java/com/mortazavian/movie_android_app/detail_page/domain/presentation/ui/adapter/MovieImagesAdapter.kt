package com.mortazavian.movie_android_app.detail_page.domain.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mortazavian.movie_android_app.R

class MovieImagesAdapter(private val context: Context, private var images: List<String>) :
    RecyclerView.Adapter<MovieImagesAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_image, parent, false)
        var animation: Animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)

        binding.animation = animation
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl = images[position]
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.itemView.findViewById(R.id.ivMovieImage)) // Ensure correct reference to ivMovieImage
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
