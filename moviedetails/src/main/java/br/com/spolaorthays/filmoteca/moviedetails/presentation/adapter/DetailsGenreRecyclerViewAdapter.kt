package br.com.spolaorthays.filmoteca.moviedetails.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.spolaorthays.filmoteca.moviedetails.R
import br.com.spolaorthays.filmoteca.moviedetails.databinding.GenreContentItemBinding
import br.com.spolaorthays.filmoteca.shared.model.MovieGenre
import com.squareup.picasso.Picasso

class DetailsGenreRecyclerViewAdapter(private var genres: List<MovieGenre>) :
    RecyclerView.Adapter<DetailsGenreRecyclerViewAdapter.DetailsGenreViewHolder>() {

    private lateinit var binding: GenreContentItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): DetailsGenreViewHolder {
        binding =
            GenreContentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailsGenreViewHolder(binding.root)
    }

    override fun getItemCount(): Int = genres.size

    override fun onBindViewHolder(holder: DetailsGenreViewHolder, position: Int) {
        val genre = genres[position]
        holder.bind(genre)
    }

    inner class DetailsGenreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(genre: MovieGenre) {
            with(binding) {
                genreName.text = genre.name
                Picasso.get()
                    .load(getIconByName(genre.name))
                    .resize(100, 100)
                    .into(genreIcon)
            }
        }

        private fun getIconByName(genreName: String): Int {
            return when (genreName) {
                "Ação" -> R.drawable.ic_action
                "Aventura" -> R.drawable.ic_adventure
                "Animação" -> R.drawable.ic_animation
                "Comédia" -> R.drawable.ic_comedy
                "Crime" -> R.drawable.ic_crime
                "Documentário" -> R.drawable.ic_documentary
                "Drama" -> R.drawable.ic_drama
                "Família" -> R.drawable.ic_family
                "Fantasia" -> R.drawable.ic_fantasy
                "História" -> R.drawable.ic_history
                "Terror" -> R.drawable.ic_horror
                "Música" -> R.drawable.ic_music
                "Mistério" -> R.drawable.ic_music
                "Romance" -> R.drawable.ic_romance
                "Ficção Científica", "Ficção científica" -> R.drawable.ic_cientific_fiction
                "Filme de TV" -> R.drawable.ic_television
                "Thriller" -> R.drawable.ic_thriller
                "Guerra" -> R.drawable.ic_war
                "Faroeste", "Ocidental" -> R.drawable.ic_western
                else -> R.drawable.ic_crime_2
            }
        }
    }
}