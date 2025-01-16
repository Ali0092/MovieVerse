package com.example.movieverse.presentation.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.movieverse.common.ScalePageTransformer
import com.example.movieverse.databinding.FragmentMainScreenBinding
import com.example.movieverse.presentation.adapters.MoviesRecyclerViewAdapter
import com.example.movieverse.presentation.adapters.TopMoviesViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private val binding: FragmentMainScreenBinding by lazy {
        FragmentMainScreenBinding.inflate(
            layoutInflater
        )
    }
    private val viewModel by activityViewModels<MainScreenViewModel>()
    private lateinit var topMoviesViewPagerAdapter: TopMoviesViewPagerAdapter
    private lateinit var upcomingMoviesRVAdapter: MoviesRecyclerViewAdapter
    private lateinit var tvShowsRVAdapter: MoviesRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //calling
        viewModel.getPopularMovies()
        viewModel.getUpcomingMovies()
        viewModel.getTVShows()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {

        topMoviesViewPagerAdapter = TopMoviesViewPagerAdapter()
        topMoviesViewPagerAdapter.submitList(mutableListOf())
        binding.vpPopularMoviesViewPager.adapter = topMoviesViewPagerAdapter
        binding.vpPopularMoviesViewPager.setPageTransformer(ScalePageTransformer())
        binding.vpPopularMoviesViewPager.offscreenPageLimit = 3
        binding.dotsIndicator.attachTo(binding.vpPopularMoviesViewPager)

        //Rvs...
        upcomingMoviesRVAdapter = MoviesRecyclerViewAdapter()
        upcomingMoviesRVAdapter.submitList(mutableListOf())
        binding.rvUpcomingMovies.adapter = upcomingMoviesRVAdapter

        tvShowsRVAdapter = MoviesRecyclerViewAdapter()
        tvShowsRVAdapter.submitList(mutableListOf())
        binding.rvTVShows.adapter = tvShowsRVAdapter


        lifecycleScope.launch {
            viewModel.popularMovies.collect {
                if (it.movies.results.isNotEmpty()) {
                    topMoviesViewPagerAdapter.submitList(it.movies.results.subList(0,7))
                    binding.vpPopularMoviesViewPager.currentItem = 2
                }
            }
        }
        lifecycleScope.launch {
            viewModel.upcomingMovies.collect { response->
                if (response.isLoading){
                    binding.shimmerUpcomingMovies.visibility = VISIBLE
                    binding.rvUpcomingMovies.visibility = GONE
                }
                if (response.movies.results.isNotEmpty()) {
                    binding.shimmerUpcomingMovies.visibility = GONE
                    binding.rvUpcomingMovies.visibility = VISIBLE
                    upcomingMoviesRVAdapter.submitList(response.movies.results.reversed())
                }

            }
        }

        lifecycleScope.launch {
            viewModel.tvShows.collect { response->
                if (response.isLoading){
                    binding.shimmerTVShows.visibility = VISIBLE
                    binding.rvTVShows.visibility = GONE
                }
                if (response.movies.results.isNotEmpty()) {
                    binding.shimmerTVShows.visibility = GONE
                    binding.rvTVShows.visibility = VISIBLE
                    tvShowsRVAdapter.submitList(response.movies.results.reversed())
                }
            }
        }

    }

}