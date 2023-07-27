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

        movieViewModel.getEndpointSession()

        observerLiveDatas()
    }

    private fun observerLiveDatas() {
        with(movieViewModel) {
            endpointList.observe(this@MainActivity) { endpoints ->
                endpoints.forEach { endpoint ->
                    movieViewModel.getMovieSessions(endpoint)
                }
            }

            allMovies.observe(this@MainActivity) { lists ->
                if (lists.size == endpointList.value?.size) {
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
            adapter =
                MovieContainerAdapter(movieList, movieViewModel.sessionList.value ?: listOf(""))
        }
    }
}
