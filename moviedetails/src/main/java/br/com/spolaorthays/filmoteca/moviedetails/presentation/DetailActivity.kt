package br.com.spolaorthays.filmoteca.moviedetails.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.GridLayoutManager
import br.com.spolaorthays.filmoteca.moviedetails.R
import br.com.spolaorthays.filmoteca.shared.R as Rshared
import br.com.spolaorthays.filmoteca.moviedetails.databinding.ActivityDetailBinding
import br.com.spolaorthays.filmoteca.moviedetails.domain.MovieDetailState
import br.com.spolaorthays.filmoteca.moviedetails.domain.QuotationState
import br.com.spolaorthays.filmoteca.moviedetails.presentation.adapter.DetailsGenreRecyclerViewAdapter
import br.com.spolaorthays.filmoteca.shared.extensions.formatDollar
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
            movieDetailState.observe(this@DetailActivity) { state ->
                when (state) {
                    is MovieDetailState.SuccessDetails -> {
                        getQuotation(state.budget)

                        state.details.also { movie ->
                            binding.detailsTitle.text = movie.movieTitle
                            Picasso.get()
                                .load("$BASE_IMAGE_LINK${movie.posterImagePath}")
                                .resize(POST_WIDTH_DETAIL_SIZE, POST_HEIGHT_DETAIL_SIZE)
                                .placeholder(Rshared.drawable.animated_progress)
                                .into(binding.detailsPoster)
                            setupRecyclerView(movie.genres)
                            binding.detailsDescription.text =
                                movie.movieDescription.ifEmpty { getString(R.string.no_description) }
                            binding.detailsDebut.text =
                                String.format(
                                    getString(R.string.release_date_text),
                                    movie.releaseDate
                                )
                            binding.detailsBudget.detailsBudgetValue.text =
                                formatDollar(movie.budget)
                            //todo Add Produtoras
                            setupVotes(movie.voteAverage)
                        }
                    }

                    is MovieDetailState.Loading -> {}
                    is MovieDetailState.Error -> {}
                }
            }
            quotationState.observe(this@DetailActivity) { state ->
                when (state) {
                    is QuotationState.Success -> {
                        calculateBudgetToReal(budget = state.budget, dollarData = state.dollarData)
                    }

                    is QuotationState.ConvertedQuotation -> {
                        binding.detailsBudget.detailsBudgetValueReal.text = state.budgetBrazil
                    }

                    is QuotationState.Loading -> {}
                    is QuotationState.Error -> {}
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
                populateMiddleStar(binding.starLayout.voteStar1)
                populateFullOrEmptyStar(binding.starLayout.voteStar2, false)
                populateFullOrEmptyStar(binding.starLayout.voteStar3, false)
                populateFullOrEmptyStar(binding.starLayout.voteStar4, false)
                populateFullOrEmptyStar(binding.starLayout.voteStar5, false)
            }

            adjustAverage > 0.7 && adjustAverage <= 1.2 -> { //1
                populateFullOrEmptyStar(binding.starLayout.voteStar1, true)
                populateFullOrEmptyStar(binding.starLayout.voteStar2, false)
                populateFullOrEmptyStar(binding.starLayout.voteStar3, false)
                populateFullOrEmptyStar(binding.starLayout.voteStar4, false)
                populateFullOrEmptyStar(binding.starLayout.voteStar5, false)
            }

            adjustAverage > 1.2 && adjustAverage <= 1.7 -> { //1 e meio
                populateFullOrEmptyStar(binding.starLayout.voteStar1, true)
                populateMiddleStar(binding.starLayout.voteStar2)
                populateFullOrEmptyStar(binding.starLayout.voteStar3, false)
                populateFullOrEmptyStar(binding.starLayout.voteStar4, false)
                populateFullOrEmptyStar(binding.starLayout.voteStar5, false)
            }

            adjustAverage > 1.7 && adjustAverage <= 2.2 -> { //2
                populateFullOrEmptyStar(binding.starLayout.voteStar1, true)
                populateFullOrEmptyStar(binding.starLayout.voteStar2, true)
                populateFullOrEmptyStar(binding.starLayout.voteStar3, false)
                populateFullOrEmptyStar(binding.starLayout.voteStar4, false)
                populateFullOrEmptyStar(binding.starLayout.voteStar5, false)
            }

            adjustAverage > 2.2 && adjustAverage <= 2.7 -> { //2 e meio
                populateFullOrEmptyStar(binding.starLayout.voteStar1, true)
                populateFullOrEmptyStar(binding.starLayout.voteStar2, true)
                populateMiddleStar(binding.starLayout.voteStar3)
                populateFullOrEmptyStar(binding.starLayout.voteStar4, false)
                populateFullOrEmptyStar(binding.starLayout.voteStar5, false)
            }

            adjustAverage > 2.7 && adjustAverage <= 3.2 -> { //3
                populateFullOrEmptyStar(binding.starLayout.voteStar1, true)
                populateFullOrEmptyStar(binding.starLayout.voteStar2, true)
                populateFullOrEmptyStar(binding.starLayout.voteStar3, true)
                populateFullOrEmptyStar(binding.starLayout.voteStar4, false)
                populateFullOrEmptyStar(binding.starLayout.voteStar5, false)
            }

            adjustAverage > 3.2 && adjustAverage <= 3.7 -> { //3 e meio
                populateFullOrEmptyStar(binding.starLayout.voteStar1, true)
                populateFullOrEmptyStar(binding.starLayout.voteStar2, true)
                populateFullOrEmptyStar(binding.starLayout.voteStar3, true)
                populateMiddleStar(binding.starLayout.voteStar4)
                populateFullOrEmptyStar(binding.starLayout.voteStar5, false)
            }

            adjustAverage > 3.7 && adjustAverage <= 4.2 -> { //4
                populateFullOrEmptyStar(binding.starLayout.voteStar1, true)
                populateFullOrEmptyStar(binding.starLayout.voteStar2, true)
                populateFullOrEmptyStar(binding.starLayout.voteStar3, true)
                populateFullOrEmptyStar(binding.starLayout.voteStar4, true)
                populateFullOrEmptyStar(binding.starLayout.voteStar5, false)
            }

            adjustAverage > 4.2 && adjustAverage <= 4.7 -> { //4 e meio
                populateFullOrEmptyStar(binding.starLayout.voteStar1, true)
                populateFullOrEmptyStar(binding.starLayout.voteStar2, true)
                populateFullOrEmptyStar(binding.starLayout.voteStar3, true)
                populateFullOrEmptyStar(binding.starLayout.voteStar4, true)
                populateMiddleStar(binding.starLayout.voteStar5)
            }

            adjustAverage > 4.7 -> {
                populateFullOrEmptyStar(binding.starLayout.voteStar1, true)
                populateFullOrEmptyStar(binding.starLayout.voteStar2, true)
                populateFullOrEmptyStar(binding.starLayout.voteStar3, true)
                populateFullOrEmptyStar(binding.starLayout.voteStar4, true)
                populateFullOrEmptyStar(binding.starLayout.voteStar5, true)
            }
        }
    }

    private fun populateFullOrEmptyStar(imageId: AppCompatImageView, isFull: Boolean) {
        val defineStar =
            if (isFull) Rshared.drawable.ic_star_full else Rshared.drawable.ic_star_empty
        Picasso.get()
            .load(defineStar)
            .placeholder(Rshared.drawable.animated_progress)
            .into(imageId)
    }

    private fun populateMiddleStar(imageId: AppCompatImageView) {
        Picasso.get()
            .load(Rshared.drawable.ic_star_middle_right)
            .placeholder(Rshared.drawable.animated_progress)
            .into(imageId)
    }
}