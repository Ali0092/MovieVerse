package com.example.movieverse.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.movieverse.R
import com.example.movieverse.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        * API READ ACCESS TOKEN : eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2YzVkNTZiZDBlM2JlNjJhNjFlNTk0YTE2OWNkZjc0NyIsIm5iZiI6MTczNjIyODg2MC4zNjA5OTk4LCJzdWIiOiI2NzdjYmZmYzU1ZDRiMzhmOTM2NzQ5YTIiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.WlW-y_sAblplZ9Bq567yILSXL9MxG-FwaJ5Ex1EH-8s
        * API KEY : 6c5d56bd0e3be62a61e594a169cdf747
        */
    }

}