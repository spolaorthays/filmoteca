package br.com.spolaorthays.filmoteca.movie.presentation.adapter

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.spolaorthays.filmoteca.shared.R as Rshared
import br.com.spolaorthays.filmoteca.movie.databinding.MovieContentItemBinding
import br.com.spolaorthays.filmoteca.shared.model.Constants.BASE_IMAGE_LINK
import br.com.spolaorthays.filmoteca.shared.model.Constants.POST_HEIGHT_LIST_SIZE
import br.com.spolaorthays.filmoteca.shared.model.Constants.POST_WIDTH_LIST_SIZE
import br.com.spolaorthays.filmoteca.shared.model.Movie
import com.squareup.picasso.Picasso

class MovieRecyclerViewAdapter(private var movies: List<Movie>) :
    RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>() {

    private lateinit var binding: MovieContentItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        binding =
            MovieContentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding.root)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemViewType(position: Int) = position

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie) {
            with(binding) {
                title.text = movie.movieTitle
                grade.text = movie.voteAverage.toString()
                cardView.setOnClickListener {
                    val deeplink = "thays://details?id=${movie.movieId}"
                    val intent = Intent(ACTION_VIEW, Uri.parse(deeplink))
                    it.context.startActivity(intent)
                }
                Picasso.get()
                    .load("$BASE_IMAGE_LINK${movie.posterImagePath}")
                    .resize(POST_WIDTH_LIST_SIZE, POST_HEIGHT_LIST_SIZE)
                    .placeholder(Rshared.drawable.animated_progress)
                    .into(posterImageView)
            }
        }
    }
}