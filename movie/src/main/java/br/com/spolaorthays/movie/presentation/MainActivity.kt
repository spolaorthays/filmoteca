package br.com.spolaorthays.movie.presentation

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.spolaorthays.filmoteca.databinding.ActivityMainBinding
import br.com.spolaorthays.movie.data.model.Endpoints.END_NOW_PLAYING
import br.com.spolaorthays.movie.data.model.Endpoints.END_POPULARS
import br.com.spolaorthays.movie.data.model.Endpoints.END_TOP_RATED
import br.com.spolaorthays.movie.data.model.Endpoints.END_UPCOMING
import br.com.spolaorthays.movie.data.model.Movie
import br.com.spolaorthays.movie.presentation.adapter.MovieContainerAdapter
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val endpointList = listOf(END_NOW_PLAYING, END_POPULARS, END_TOP_RATED, END_UPCOMING)

    @Inject
    lateinit var movieViewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        endpointList.forEachIndexed { index, endpoint ->
            movieViewModel.getMovieSessions(endpoint, index)
        }

        observerLiveDatas()
    }

    private fun observerLiveDatas() {
        with(movieViewModel) {
            upcomingMovieList.observe(this@MainActivity) {
                allMovies.value =
                    mutableListOf(
                        nowPlayingList.value ?: listOf(),
                        popularMovieList.value ?: listOf(),
                        topRatedMovieList.value ?: listOf(),
                        it
                    )
            }
            allMovies.observe(this@MainActivity) { lists ->
                if (lists.size == endpointList.size) {
                    setupRecycler(lists)
                }
            }
        }
    }

    private fun setupRecycler(movieList: List<List<Movie>>) {
        binding.recyclerBase.apply {
            this.visibility = View.VISIBLE
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = MovieContainerAdapter(movieList)
        }
    }
}
