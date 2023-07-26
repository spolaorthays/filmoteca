package br.com.spolaorthays.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.spolaorthays.filmoteca.R
import br.com.spolaorthays.filmoteca.databinding.MovieContentItemBinding
import br.com.spolaorthays.movie.data.model.Movie
import com.squareup.picasso.Picasso

internal const val BASE_IMAGE_LINK = "https://image.tmdb.org/t/p/original/"

class MovieRecyclerViewAdapter(private var movies: List<Movie>) : RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>() {

    private lateinit var binding: MovieContentItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        binding = MovieContentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
            binding.title.text = movie.movieTitle
            binding.grade.text = movie.voteAverage.toString()
            Picasso.get()
                .load("$BASE_IMAGE_LINK${movie.posterImagePath}")
                .resize(400, 600)
                .placeholder(R.drawable.animated_progress)
                .into(binding.posterImageView)
        }
    }
}