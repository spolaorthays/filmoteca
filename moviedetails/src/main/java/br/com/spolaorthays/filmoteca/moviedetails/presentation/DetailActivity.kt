package br.com.spolaorthays.filmoteca.moviedetails.presentation

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import br.com.spolaorthays.filmoteca.shared.R as Rshared
import br.com.spolaorthays.filmoteca.moviedetails.databinding.ActivityDetailBinding
import br.com.spolaorthays.filmoteca.moviedetails.presentation.adapter.DetailsGenreRecyclerViewAdapter
import br.com.spolaorthays.filmoteca.shared.model.Constants.BASE_IMAGE_LINK
import br.com.spolaorthays.filmoteca.shared.model.Constants.HEIGHT_SIZE
import br.com.spolaorthays.filmoteca.shared.model.Constants.WIDTH_SIZE
import br.com.spolaorthays.filmoteca.shared.model.MovieGenre
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val parameters: String by lazy {
        intent.data?.getQueryParameter("id") as String
    }

    @Inject
    lateinit var detailViewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel.getDetails(parameters.toInt())

        observeLiveData()
    }

    private fun observeLiveData() {
        with(detailViewModel) {
            movieDetail.observe(this@DetailActivity) { details ->
                details?.let {
                    binding.detailsTitle.text = it.originalTitle
                    Picasso.get()
                        .load("$BASE_IMAGE_LINK${it.posterImagePath}")
                        .resize(WIDTH_SIZE, HEIGHT_SIZE)
                        //TODO Problema do shared resolvido, agora posso migrar todas as imagens pro shared
                        .placeholder(Rshared.drawable.loading_progress_2)
                        .into(binding.detailsPoster)
                    setupRecyclerView(it.genres)
                }
            }
        }
    }

    private fun setupRecyclerView(genreList: List<MovieGenre>) {
        binding.detailsGenreList.apply {
            this.visibility = View.VISIBLE
            layoutManager =
                GridLayoutManager(this@DetailActivity, 2)
            adapter =
                DetailsGenreRecyclerViewAdapter(genreList)
        }
    }
}