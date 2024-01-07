package br.com.spolaorthays.filmoteca.moviedetails.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.spolaorthays.filmoteca.shared.R as Rshared
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
                    .resize(70, 70)
                    .into(genreIcon)
            }
        }

        private fun getIconByName(genreName: String): Int {
            return when (genreName) {
                "Ação" -> Rshared.drawable.ic_action
                "Aventura" -> Rshared.drawable.ic_adventure
                "Animação" -> Rshared.drawable.ic_animation
                "Comédia" -> Rshared.drawable.ic_comedy
                "Crime" -> Rshared.drawable.ic_crime
                "Documentário" -> Rshared.drawable.ic_documentary
                "Drama" -> Rshared.drawable.ic_drama
                "Família" -> Rshared.drawable.ic_family
                "Fantasia" -> Rshared.drawable.ic_fantasy
                "História" -> Rshared.drawable.ic_history
                "Terror" -> Rshared.drawable.ic_horror
                "Música" -> Rshared.drawable.ic_music
                "Mistério" -> Rshared.drawable.ic_music
                "Romance" -> Rshared.drawable.ic_romance
                "Ficção Científica", "Ficção científica" -> Rshared.drawable.ic_cientific_fiction
                "Filme de TV" -> Rshared.drawable.ic_television
                "Thriller" -> Rshared.drawable.ic_thriller
                "Guerra" -> Rshared.drawable.ic_war
                "Faroeste", "Ocidental" -> Rshared.drawable.ic_western
                else -> Rshared.drawable.ic_crime_2
            }
        }
    }
}