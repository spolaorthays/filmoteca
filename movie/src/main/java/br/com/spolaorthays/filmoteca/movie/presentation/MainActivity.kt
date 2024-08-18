package br.com.spolaorthays.filmoteca.movie.presentation

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.spolaorthays.filmoteca.movie.databinding.ActivityMainBinding
import br.com.spolaorthays.filmoteca.movie.domain.states.MovieState.Loading
import br.com.spolaorthays.filmoteca.movie.domain.states.MovieState.NextSession
import br.com.spolaorthays.filmoteca.movie.domain.states.MovieState.SuccessAllMovieList
import br.com.spolaorthays.filmoteca.movie.domain.states.MovieState.Error
import br.com.spolaorthays.filmoteca.shared.model.Movie
import br.com.spolaorthays.filmoteca.movie.presentation.adapter.MovieContainerAdapter
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var endpointList: List<String>

    @Inject
    lateinit var movieViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        endpointList = movieViewModel.getEndpointSession()

        observerLiveDatas()
    }

    private fun observerLiveDatas() {
        with(movieViewModel) {
            movieState.observe(this@MainActivity) { state ->
                when(state) {
                    is Loading -> {
                        binding.loadingImage.visibility = View.VISIBLE
                        movieViewModel.getMovieSessions(endpointList[0], position = 0)
                    }
                    is NextSession -> {
                        movieViewModel.getMovieSessions(endpointList[state.next], position = state.next)
                    }
                    is SuccessAllMovieList -> {
                        binding.loadingImage.visibility = View.GONE
                        setupRecycler(state.completeList)
                    }
                    is Error -> {}
                }

            }
        }
    }

    private fun setupRecycler(movieList: List<List<Movie>>) {
        binding.recyclerBase.apply {
            this.visibility = View.VISIBLE
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter =
                MovieContainerAdapter(movieList, movieViewModel.sessionList.value ?: listOf(""))
        }
    }
}
