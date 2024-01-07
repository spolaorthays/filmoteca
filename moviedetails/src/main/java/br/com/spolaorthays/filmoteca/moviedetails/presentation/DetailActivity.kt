package br.com.spolaorthays.filmoteca.moviedetails.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.GridLayoutManager
import br.com.spolaorthays.filmoteca.shared.R as Rshared
import br.com.spolaorthays.filmoteca.moviedetails.databinding.ActivityDetailBinding
import br.com.spolaorthays.filmoteca.moviedetails.presentation.adapter.DetailsGenreRecyclerViewAdapter
import br.com.spolaorthays.filmoteca.shared.model.Constants.BASE_IMAGE_LINK
import br.com.spolaorthays.filmoteca.shared.model.Constants.POST_HEIGHT_DETAIL_SIZE
import br.com.spolaorthays.filmoteca.shared.model.Constants.POST_WIDTH_DETAIL_SIZE
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
                details?.let { movie ->
                    binding.detailsTitle.text = movie.movieTitle
                    Picasso.get()
                        .load("$BASE_IMAGE_LINK${movie.posterImagePath}")
                        .resize(POST_WIDTH_DETAIL_SIZE, POST_HEIGHT_DETAIL_SIZE)
                        .placeholder(Rshared.drawable.loading_progress_2)
                        .into(binding.detailsPoster)
                    setupRecyclerView(movie.genres)
                    binding.detailsDescription.text = movie.movieDescription
                    //Data de estreia
                    //nota em forma de estrelas
                    //Produtoras
                    //orçamento (colocar cifrão)
                    setupVotes(movie.voteAverage)
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

    private fun setupVotes(voteAverage: Float) {
        val adjustAverage = voteAverage * 0.5
        when {

            adjustAverage <= 0.7 -> { //meio
                populateMiddleStar(binding.voteStar1)
                populateFullOrEmptyStar(binding.voteStar2, false)
                populateFullOrEmptyStar(binding.voteStar3, false)
                populateFullOrEmptyStar(binding.voteStar4, false)
                populateFullOrEmptyStar(binding.voteStar5, false)
            }

            adjustAverage > 0.7 && adjustAverage <= 1.2 -> { //1
                populateFullOrEmptyStar(binding.voteStar1, true)
                populateFullOrEmptyStar(binding.voteStar2, false)
                populateFullOrEmptyStar(binding.voteStar3, false)
                populateFullOrEmptyStar(binding.voteStar4, false)
                populateFullOrEmptyStar(binding.voteStar5, false)
            }

            adjustAverage > 1.2 && adjustAverage <= 1.7 -> { //1 e meio
                populateFullOrEmptyStar(binding.voteStar1, true)
                populateMiddleStar(binding.voteStar2)
                populateFullOrEmptyStar(binding.voteStar3, false)
                populateFullOrEmptyStar(binding.voteStar4, false)
                populateFullOrEmptyStar(binding.voteStar5, false)
            }

            adjustAverage > 1.7 && adjustAverage <= 2.2 -> { //2
                populateFullOrEmptyStar(binding.voteStar1, true)
                populateFullOrEmptyStar(binding.voteStar2, true)
                populateFullOrEmptyStar(binding.voteStar3, false)
                populateFullOrEmptyStar(binding.voteStar4, false)
                populateFullOrEmptyStar(binding.voteStar5, false)
            }

            adjustAverage > 2.2 && adjustAverage <= 2.7 -> { //2 e meio
                populateFullOrEmptyStar(binding.voteStar1, true)
                populateFullOrEmptyStar(binding.voteStar2, true)
                populateMiddleStar(binding.voteStar3)
                populateFullOrEmptyStar(binding.voteStar4, false)
                populateFullOrEmptyStar(binding.voteStar5, false)
            }

            adjustAverage > 2.7 && adjustAverage <= 3.2 -> { //3
                populateFullOrEmptyStar(binding.voteStar1, true)
                populateFullOrEmptyStar(binding.voteStar2, true)
                populateFullOrEmptyStar(binding.voteStar3, true)
                populateFullOrEmptyStar(binding.voteStar4, false)
                populateFullOrEmptyStar(binding.voteStar5, false)
            }

            adjustAverage > 3.2 && adjustAverage <= 3.7 -> { //3 e meio
                populateFullOrEmptyStar(binding.voteStar1, true)
                populateFullOrEmptyStar(binding.voteStar2, true)
                populateFullOrEmptyStar(binding.voteStar3, true)
                populateMiddleStar(binding.voteStar4)
                populateFullOrEmptyStar(binding.voteStar5, false)
            }

            adjustAverage > 3.7 && adjustAverage <= 4.2 -> { //4
                populateFullOrEmptyStar(binding.voteStar1, true)
                populateFullOrEmptyStar(binding.voteStar2, true)
                populateFullOrEmptyStar(binding.voteStar3, true)
                populateFullOrEmptyStar(binding.voteStar4, true)
                populateFullOrEmptyStar(binding.voteStar5, false)
            }

            adjustAverage > 4.2 && adjustAverage <= 4.7 -> { //4 e meio
                populateFullOrEmptyStar(binding.voteStar1, true)
                populateFullOrEmptyStar(binding.voteStar2, true)
                populateFullOrEmptyStar(binding.voteStar3, true)
                populateFullOrEmptyStar(binding.voteStar4, true)
                populateMiddleStar(binding.voteStar5)
            }

            adjustAverage > 4.7 -> {
                populateFullOrEmptyStar(binding.voteStar1, true)
                populateFullOrEmptyStar(binding.voteStar2, true)
                populateFullOrEmptyStar(binding.voteStar3, true)
                populateFullOrEmptyStar(binding.voteStar4, true)
                populateFullOrEmptyStar(binding.voteStar5, true)
            }
        }
    }

    private fun populateFullOrEmptyStar(imageId: AppCompatImageView, isFull: Boolean) {
        val defineStar =
            if (isFull) Rshared.drawable.ic_star_full else Rshared.drawable.ic_star_empty
        Picasso.get()
            .load(defineStar)
            .placeholder(Rshared.drawable.loading_progress_2)
            .into(imageId)
    }

    private fun populateMiddleStar(imageId: AppCompatImageView) {
        Picasso.get()
            .load(Rshared.drawable.ic_star_middle_right)
            .placeholder(Rshared.drawable.loading_progress_2)
            .into(imageId)
    }
}