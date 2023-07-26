package br.com.spolaorthays.movie.presentation

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.spolaorthays.filmoteca.databinding.ActivityMainBinding
import br.com.spolaorthays.movie.data.model.Movie
import br.com.spolaorthays.movie.presentation.adapter.MovieContainerAdapter
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var movieViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieViewModel.getAllMovieSession()

        observerLiveDatas()
    }

    private fun observerLiveDatas() {
        movieViewModel.movieList.observe(this) {
            if (it.isNullOrEmpty().not()) {
                //setupRecycler(it)
            }
        }

        movieViewModel.popularMovieList.observe(this) {
            if (it.isNullOrEmpty().not()) {
                setupRecycler2(it)
            }
        }
    }

    private fun setupRecycler2(movieList: List<Movie>) {
        val dualList = listOf(movieViewModel.movieList.value ?: listOf(), movieViewModel.popularMovieList.value ?: listOf())

        binding.recyclerBase.apply {
            this.visibility = View.VISIBLE
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = MovieContainerAdapter(dualList)
        }
    }
}