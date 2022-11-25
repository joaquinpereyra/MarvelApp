package com.notableFactory.marvelapp.ui.home

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.notableFactory.marvelapp.databinding.FragmentBrowseBinding
import com.notableFactory.marvelapp.model.SuperHero
import com.notableFactory.marvelapp.ui.adapters.HeroesListAdapter
import com.notableFactory.marvelapp.ui.login.LoginFragment
import com.notableFactory.marvelapp.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.Serializable
import java.lang.ClassCastException


class BrowseFragment : Fragment() {

    private var _binding: FragmentBrowseBinding? = null
    private val binding: FragmentBrowseBinding
        get() = _binding!!

    private val homeViewModel by viewModel<HomeViewModel>()
    private val adapter = HeroesListAdapter()
    private lateinit var listener: OnBrowseFragmentInteractionListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter.setOnItemClickListener(object : HeroesListAdapter.onItemClickListener {
            override fun onItemClick(heroe: SuperHero, imageView: ImageView) {

                listener.onSuperHeroClick(heroe, imageView)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentBrowseBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BrowseFragment.OnBrowseFragmentInteractionListener) {
            listener = context
        }
        else {
            throw ClassCastException("Must implement BrowseFragment.OnBrowseFragmentInteractionListener")
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchCharacterView.setBackgroundColor(Color.WHITE)
        val heroesRecyclerView:RecyclerView = binding.characterRecyclerView
        heroesRecyclerView.layoutManager = GridLayoutManager(activity,2)
        heroesRecyclerView.adapter = adapter

        binding.searchCharacterView.setOnQueryTextListener(object  : android.widget.SearchView.OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                updateCharacters(query)
                return false
            }
            //TODO implementar timer
            override fun onQueryTextChange(newText: String?): Boolean {
                updateCharacters(newText)
                return false
            }
        })

        setObservers()




    }
    fun updateCharacters(text:String?){
        if (!text.isNullOrEmpty()) {
            homeViewModel.searchByNameStartWith(text)
        }else{
            homeViewModel.fetchCharacters()
        }
    }
    private fun setObservers() {
        homeViewModel.heroesList.observe(viewLifecycleOwner) { superHeroList ->
            adapter.setData(superHeroList)
        }

    }

    companion object {
        const val TAG = "BrowseFragment"

        @JvmStatic
        fun newInstance(adapter: Serializable) = BrowseFragment().apply {
            arguments = Bundle().apply {
                putSerializable("adapter", adapter)
            }
        }

    }
    interface OnBrowseFragmentInteractionListener {
        fun onSuperHeroClick(heroe : SuperHero, imageView : ImageView)
    }


}