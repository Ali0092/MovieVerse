package com.example.movieverse.presentation.movie_details_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.movieverse.databinding.FragmentMovieDetailBinding
import com.example.movieverse.presentation.main_screen.MainScreenViewModel
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private val binding: FragmentMovieDetailBinding by lazy { FragmentMovieDetailBinding.inflate(layoutInflater) }
    private val viewModel by activityViewModels<MainScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        viewModel.selectedMovie.observe(viewLifecycleOwner) { selectedMovie->
            Log.d("MovieDetailsScreenLogs", "setUpViews: ${selectedMovie}")
            binding.ivMoviePoster.load("https://image.tmdb.org/t/p/w500/${selectedMovie.posterPath}") {
                placeholder(ShimmerDrawable().apply {
                    setShimmer(
                        Shimmer.AlphaHighlightBuilder().setDuration(500).setBaseAlpha(0.97f)
                            .setHighlightAlpha(0.9f).setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                            .setAutoStart(true).build()
                    )
                })
            }
            binding.tvMovieName.text = selectedMovie.title
            binding.tvMovieRating.text = selectedMovie.voteAverage.toString()
            binding.tvMovieOverView.text = selectedMovie.overview

        }

        binding.cBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        activity?.onBackPressedDispatcher?.addCallback(object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })

    }

}