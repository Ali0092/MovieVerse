package com.example.movieverse.presentation.main_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.movieverse.R
import com.example.movieverse.databinding.FragmentMainScreenBinding
import com.example.movieverse.presentation.adapters.TopMoviesViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private val binding: FragmentMainScreenBinding by lazy {  FragmentMainScreenBinding.inflate(layoutInflater) }
    private val viewModel by activityViewModels<MainScreenViewModel>()
    private lateinit var topMoviesViewPagerAdapter: TopMoviesViewPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View  = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

    }


    private fun setupViews() {

        viewModel.getPopularMovies()

        topMoviesViewPagerAdapter= TopMoviesViewPagerAdapter()
        topMoviesViewPagerAdapter.submitList(mutableListOf())
        binding.vpTopMoviesViewPager.adapter = topMoviesViewPagerAdapter

        lifecycleScope.launchWhenCreated {
            viewModel.movies.collect{
                Log.d("MoviesSceneLogs", "onViewCreated: ${it}")

                if (it.movies.results.isNotEmpty()){
                    topMoviesViewPagerAdapter.submitList(it.movies.results)
                }

            }
        }

    }

}