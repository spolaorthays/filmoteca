package br.com.spolaorthays.filmoteca.moviedetails.presentation

import android.os.Bundle
import br.com.spolaorthays.filmoteca.databinding.ActivityDetailBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val parameters: String by lazy {
        intent.data?.getQueryParameter("id") as String
    }

    @Inject
    lateinit var viewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getDetails(parameters.toInt())
    }
}