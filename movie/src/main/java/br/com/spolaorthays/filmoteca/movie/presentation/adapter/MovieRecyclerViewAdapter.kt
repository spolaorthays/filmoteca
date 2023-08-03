package br.com.spolaorthays.filmoteca.movie.presentation.adapter

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.Intent.FLAG_ACTIVITY_REQUIRE_NON_BROWSER
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.spolaorthays.filmoteca.R
import br.com.spolaorthays.filmoteca.databinding.MovieContentItemBinding
import br.com.spolaorthays.filmoteca.movie.data.model.Constants.HEIGHT_SIZE
import br.com.spolaorthays.filmoteca.movie.data.model.Constants.WIDTH_SIZE
import br.com.spolaorthays.filmoteca.shared.model.Movie
import com.squareup.picasso.Picasso

internal const val BASE_IMAGE_LINK = "https://image.tmdb.org/t/p/original/"

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
                    val deeplink = "spolaorthays://details?id=${movie.movieId}"
                    openDeeplink(deeplink, binding.root.context)
//                    binding.root.context.startActivity(
//                        Intent(Intent.ACTION_VIEW, Uri.parse(deeplink))
////                            .apply {
////                            data = Uri.parse(deeplink)
////                        }
//                    )
                }
                Picasso.get()
                    .load("$BASE_IMAGE_LINK${movie.posterImagePath}")
                    .resize(WIDTH_SIZE, HEIGHT_SIZE)
                    .placeholder(R.drawable.animated_progress)
                    .into(posterImageView)
            }
        }

        private fun openDeeplink(url: String, context: Context) {
            try {
                val intent = Intent(ACTION_VIEW, Uri.parse(url)).apply {
                    // The URL should either launch directly in a non-browser app
                    // (if it’s the default), or in the disambiguation dialog
                    //addCategory(CATEGORY_BROWSABLE)
                    flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_REQUIRE_NON_BROWSER
                }
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                e.message
                println(e.message)
            }
        }
    }
}