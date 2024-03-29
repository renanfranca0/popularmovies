package com.example.filmespopulares.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmespopulares.R
import com.example.filmespopulares.adapter.FavoriteMovieAdapter
import com.example.filmespopulares.databinding.FragmentFavoritesBinding


class FavoritesFragment : Fragment() {


    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: FavoriteMovieAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var mFavoriteMoviesViewModel: FavoriteMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enable navigation through options menu
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView

        getMoviesFromDatabase()
    }

    fun getMoviesFromDatabase() {
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        adapter = FavoriteMovieAdapter()
        recyclerView.adapter = adapter

        // Favorite View Model
        mFavoriteMoviesViewModel = ViewModelProvider(this).get(FavoriteMoviesViewModel::class.java)
        // observing the live data to change content of view holder only when there is a new
        // data to fill
        mFavoriteMoviesViewModel.readlAllData.observe(viewLifecycleOwner, Observer { movie ->
            if (movie.size > 0) {
                adapter.setData(movie)
            } else {
                val imageNoFavorite: ImageView = requireView().findViewById(R.id.image_no_favorite)
                imageNoFavorite.visibility = VISIBLE
                val textNoFavorite: TextView = requireView().findViewById(R.id.text_no_favorite)
                textNoFavorite.visibility = VISIBLE
            }

        })
    }

}