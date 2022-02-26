package com.roberts.tvshows.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.roberts.tvshows.R
import com.roberts.tvshows.adapter.TvShowsAdapter
import com.roberts.tvshows.databinding.ActivityMainBinding
import com.roberts.tvshows.util.Resource
import com.roberts.tvshows.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { TvShowsAdapter() }
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribeToPosts()
    }

    private fun subscribeToPosts() {
        viewModel.shows.observe(this, Observer { result ->
            when(result){
                is Resource.Loading ->{
                    binding.progressBar.isVisible = true
                }
                is Resource.Success ->{
                    binding.progressBar.isVisible = false
                    val shows = result.data
                    adapter.submitList(shows)
                    binding.tvShowsRV.adapter = adapter
                }
                is Resource.Error ->{
                    binding.progressBar.isVisible = false
                    Snackbar.make(binding.root, result.message.toString(), Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }
}