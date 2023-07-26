package br.com.spolaorthays.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.spolaorthays.filmoteca.databinding.MovieContainerItemBinding
import br.com.spolaorthays.movie.data.model.Movie

class MovieContainerAdapter(private val movieList: List<List<Movie>>) :
    RecyclerView.Adapter<MovieContainerAdapter.ContainerViewHolder>() {

    private lateinit var binding: MovieContainerItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContainerViewHolder {
        binding =
            MovieContainerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContainerViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ContainerViewHolder, position: Int) {
        val list = movieList[position]
        holder.bind(list)
    }

    inner class ContainerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(list: List<Movie>) {
            binding.recyclerContainer.apply {
                layoutManager =
                    LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = MovieRecyclerViewAdapter(list)
            }
        }
    }
}