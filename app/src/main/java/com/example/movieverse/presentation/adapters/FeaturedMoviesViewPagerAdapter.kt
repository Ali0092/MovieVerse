package com.example.movieverse.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieverse.databinding.FeaturedMoviesSingleItemBinding
import com.example.movieverse.databinding.TopMoviesSingleItemBinding
import com.example.movieverse.domain.model.MoviesModel
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

class FeaturedMoviesRecyclerViewAdapter: RecyclerView.Adapter<FeaturedMoviesRecyclerViewAdapter.TopMoviesViewHolder>() {

    private var moviesList = emptyList<MoviesModel.Result>()

    inner class TopMoviesViewHolder(private val binding: FeaturedMoviesSingleItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bindViews(movieItem: MoviesModel.Result){
            binding.ivMoviePoster.load("https://image.tmdb.org/t/p/w500/${movieItem.posterPath}"){
                placeholder(ShimmerDrawable().apply {
                    setShimmer(
                        Shimmer.AlphaHighlightBuilder()
                            .setDuration(500)
                            .setBaseAlpha(0.97f)
                            .setHighlightAlpha(0.9f)
                            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                            .setAutoStart(true)
                            .build()
                    )
                })
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMoviesViewHolder {
        return TopMoviesViewHolder(FeaturedMoviesSingleItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TopMoviesViewHolder, position: Int) {
        holder.bindViews(moviesList[position])
    }

    override fun getItemCount(): Int = moviesList.size

    fun submitList(list: List<MoviesModel.Result>){
        moviesList = list
        notifyDataSetChanged()

    }

}