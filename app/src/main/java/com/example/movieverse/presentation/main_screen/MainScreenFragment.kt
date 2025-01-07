package com.example.movieverse.presentation.main_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.movieverse.R
import com.example.movieverse.databinding.FragmentMainScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private val binding: FragmentMainScreenBinding by lazy {  FragmentMainScreenBinding.inflate(layoutInflater) }
    private val viewModel by activityViewModels<MainScreenViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View  = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tempButton.setOnClickListener {
            viewModel.getPopularMovies()
        }

    }


}