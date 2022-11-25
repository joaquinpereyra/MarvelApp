package com.notableFactory.marvelapp.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.databinding.FragmentBrowseBinding
import com.notableFactory.marvelapp.databinding.FragmentFavoritesBinding
import com.notableFactory.marvelapp.model.SuperHero
import com.notableFactory.marvelapp.ui.adapters.FavoritesHeroesListAdapter
import com.notableFactory.marvelapp.ui.adapters.HeroesListAdapter
import java.lang.ClassCastException

class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding: FragmentFavoritesBinding
        get() = _binding!!

    private val adapter = FavoritesHeroesListAdapter()
    private lateinit var listener: FavoritesFragment.OnFavoritesFragmentInteractionListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter.setOnItemClickListener(object : FavoritesHeroesListAdapter.onItemClickListener {
            override fun onItemClick(favoritehero: SuperHero) {

                listener.onFavoriteHeroClick(favoritehero)
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val favoritesHeroesRecycledView: RecyclerView = binding.favoritesHeroesRecycledView
        favoritesHeroesRecycledView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        favoritesHeroesRecycledView.adapter = adapter

    }
/*
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FavoritesFragment.OnFavoritesFragmentInteractionListener) {
            listener = context
        }
        else {
            throw ClassCastException("Must implement BrowseFragment.OnBrowseFragmentInteractionListener")
        }

    }/**/

    private fun setObservers() {
        homeViewModel.heroesList.observe(viewLifecycleOwner) { favoritesHeroesList ->
            adapter.setData(favoritesHeroesList)
        }

    }*/


    interface OnFavoritesFragmentInteractionListener {
        fun onFavoriteHeroClick(favoritehero : SuperHero)
    }



}