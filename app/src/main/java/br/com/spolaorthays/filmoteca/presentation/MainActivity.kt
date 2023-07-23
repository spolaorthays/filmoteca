package br.com.spolaorthays.filmoteca.presentation

import android.os.Bundle
import br.com.spolaorthays.filmoteca.databinding.ActivityMainBinding
import br.com.spolaorthays.movie.presentation.MovieViewModel
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

        movieViewModel.getMovieTitle()

        provideTitleFromAPI()
    }

    private fun provideTitleFromAPI() {
        movieViewModel.title.observe(this) {
            binding.textWelcome.text = it
        }
    }
}