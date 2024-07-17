//package com.mortazavian.movie_android_app.detail_page
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.navigation.fragment.navArgs
//import com.mortazavian.movie_android_app.databinding.FragmentDetailBinding
//
//class DetailFragment : Fragment() {
//
//    private lateinit var binding: FragmentDetailBinding
//    private val args: DetailFragmentArgs by navArgs()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentDetailBinding.inflate(layoutInflater)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val movieId = args.movieId
//        // Use movieId to fetch details and update UI
//    }
//}



//import android.graphics.Movie
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import com.mortazavian.movie_android_app.databinding.FragmentDetailBinding
//import com.mortazavian.movie_android_app.home_page.domain.data.model.MovieForMainPage
//
//class DetailFragment : Fragment() {
//
//    private lateinit var binding: FragmentDetailBinding
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentDetailBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // Retrieve movie details from arguments
//        val movie = arguments?.getParcelable<MovieForMainPage>("movie")
//
//        movie?.let {
//            // Set movie details in UI
//            binding.tvMovieTitle.text = it.title
//            binding.tvRating.text = it.imdbRating
////            binding.tvDuration.text = it.duration
//            binding.tvReleaseDate.text = it.year
////            binding.tvSummary.text = it.summary
////            binding.tvActors.text = it.actors.joinToString(", ")
//            // Set additional UI elements like image, etc.
//        }
//    }
//
//    companion object {
//        fun newInstance(movie: Movie): DetailFragment {
//            val fragment = DetailFragment()
//            val args = Bundle().apply {
//                putParcelable("movie", movie)
//            }
//            fragment.arguments = args
//            return fragment
//        }
//    }
//}
