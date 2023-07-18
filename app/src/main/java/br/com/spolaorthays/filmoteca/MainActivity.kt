package br.com.spolaorthays.filmoteca

import android.os.Bundle
import androidx.lifecycle.Observer
import br.com.spolaorthays.filmoteca.databinding.ActivityMainBinding
import br.com.spolaorthays.filmoteca.presentation.MovieViewModel
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.observers.SafeObserver
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

        //binding.textWelcome.text = movieViewModel.title.value
        test()
    }

    private fun test() {
        movieViewModel.title.observe(this, Observer {
            binding.textWelcome.text = it
        })
    }
}