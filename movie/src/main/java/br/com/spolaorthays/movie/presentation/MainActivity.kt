package br.com.spolaorthays.movie.presentation

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.spolaorthays.filmoteca.databinding.ActivityMainBinding
import br.com.spolaorthays.movie.data.model.Movie
import br.com.spolaorthays.movie.presentation.adapter.MovieRecyclerViewAdapter
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

        movieViewModel.getMovieSession()
        movieViewModel.getPopularMovieSession()

        observerLiveDatas()
    }

    private fun observerLiveDatas() {
        movieViewModel.movieList.observe(this) {
            if (it.isNullOrEmpty().not()) {
                setupRecycler(it)
            }
        }

        movieViewModel.popularMovieList.observe(this) {
            if (it.isNullOrEmpty().not()) {
                setupRecycler2(it)
            }
        }
    }

    //TODO Minimizar os códigos repetidos adaptando o código, posso fazer adicionando views ou usando um adapter que adiciona essas views
    private fun setupRecycler(movieList: List<Movie>) {
        binding.recyclerMovie.apply {
            this.visibility = View.VISIBLE
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = MovieRecyclerViewAdapter(movieList)
        }
    }

    private fun setupRecycler2(movieList: List<Movie>) {
        binding.recyclerMovie2.apply {
            this.visibility = View.VISIBLE
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = MovieRecyclerViewAdapter(movieList)
        }
    }
}