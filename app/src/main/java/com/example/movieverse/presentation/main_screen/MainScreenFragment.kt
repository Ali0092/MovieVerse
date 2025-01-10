package com.example.movieverse.presentation.main_screen

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.movieverse.R
import com.example.movieverse.common.ScalePageTransformer
import com.example.movieverse.common.SliderTransformer
import com.example.movieverse.databinding.FragmentMainScreenBinding
import com.example.movieverse.presentation.adapters.FeaturedMoviesRecyclerViewAdapter
import com.example.movieverse.presentation.adapters.TopMoviesViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private val binding: FragmentMainScreenBinding by lazy {  FragmentMainScreenBinding.inflate(layoutInflater) }
    private val viewModel by activityViewModels<MainScreenViewModel>()
    private lateinit var topMoviesViewPagerAdapter: TopMoviesViewPagerAdapter
    private lateinit var featuredMoviesViewPagerAdapter: FeaturedMoviesRecyclerViewAdapter
    private lateinit var upcomingMoviesViewPagerAdapter: FeaturedMoviesRecyclerViewAdapter

    var goingLeft: Boolean by Delegates.notNull()
    private var lastOffset = 0f
    var progress: Float by Delegates.notNull()

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
        binding.vpTopMoviesViewPager.setPageTransformer(ScalePageTransformer())
        binding.vpTopMoviesViewPager.offscreenPageLimit = 3


//        class HorizontalMarginItemDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {
//            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
//                outRect.right = margin
//                outRect.left = margin
//            }
//        }
//
//        // Apply the decoration
//        // val margin = resources.getD
//        binding.vpTopMoviesViewPager.addItemDecoration(HorizontalMarginItemDecoration(0))

        //Rvs...
        featuredMoviesViewPagerAdapter = FeaturedMoviesRecyclerViewAdapter()
        featuredMoviesViewPagerAdapter.submitList(mutableListOf())
        binding.rvFeaturedMovies.adapter = featuredMoviesViewPagerAdapter

        upcomingMoviesViewPagerAdapter = FeaturedMoviesRecyclerViewAdapter()
        upcomingMoviesViewPagerAdapter.submitList(mutableListOf())
        binding.rvUpcomingMovies.adapter = upcomingMoviesViewPagerAdapter


        lifecycleScope.launchWhenCreated {
            viewModel.movies.collect{
                if (it.movies.results.isNotEmpty()){
                    topMoviesViewPagerAdapter.submitList(it.movies.results)
                    binding.vpTopMoviesViewPager.setCurrentItem(2)
                    featuredMoviesViewPagerAdapter.submitList(it.movies.results.reversed())
                    upcomingMoviesViewPagerAdapter.submitList(it.movies.results)
                }
            }
        }

//        binding.vpTopMoviesViewPager.registerOnPageChangeCallback(object : OnPageChangeCallback(){
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
//                val realCurrentPosition: Int
//                val nextPosition: Int
//                val realOffset: Float
//                goingLeft = lastOffset > positionOffset
//                if (goingLeft) {
//                    realCurrentPosition = position + 1
//                    nextPosition = position
//                    realOffset = 1 - positionOffset
//                } else {
//                    nextPosition = position + 1
//                    realCurrentPosition = position
//                    realOffset = positionOffset
//                }
//
//                val currentCard = (binding.vpTopMoviesViewPager[0] as RecyclerView).layoutManager?.findViewByPosition(realCurrentPosition)
//                currentCard?.scaleX = (1 + 0.4 * (1 - realOffset)).toFloat()
//                currentCard?.scaleY = (1 + 0.4 * (1 - realOffset)).toFloat()
//                currentCard?.pivotY = 0f
//
//                val nextCard = (binding.vpTopMoviesViewPager[0] as RecyclerView).layoutManager?.findViewByPosition(nextPosition)
//                nextCard?.scaleX = (1 + 0.4 * realOffset).toFloat()
//                nextCard?.scaleY = (1 + 0.4 * realOffset).toFloat()
//                nextCard?.pivotY = 0f
//
//                lastOffset = positionOffset
//                progress = when (position) {
//                    position -> positionOffset
//                    position + 1 -> 1 - positionOffset
//                    position - 1 -> 1 - positionOffset
//                    else -> 0f
//                }
//            }
//
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {
//                super.onPageScrollStateChanged(state)
//            }
//        })

    }

}