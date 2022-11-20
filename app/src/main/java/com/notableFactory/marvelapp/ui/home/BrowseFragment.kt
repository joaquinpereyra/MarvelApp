package com.notableFactory.marvelapp.ui.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.notableFactory.marvelapp.databinding.FragmentBrowseBinding
import com.notableFactory.marvelapp.model.SuperHero
import com.notableFactory.marvelapp.ui.adapters.HeroesListAdapter
import com.notableFactory.marvelapp.ui.login.LoginFragment
import com.notableFactory.marvelapp.viewmodel.HomeViewModel
import java.io.Serializable
import java.lang.ClassCastException


class BrowseFragment : Fragment() {

    private var _binding: FragmentBrowseBinding? = null
    private val binding: FragmentBrowseBinding
        get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: HeroesListAdapter
    private lateinit var listener: OnBrowseFragmentInteractionListener

    private var heroesList: MutableList<SuperHero> = emptyList<SuperHero>().toMutableList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            adapter = it.getSerializable("adapter") as HeroesListAdapter
        }
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
       // val repository = MarvelCharactersRepository
       // factory = HomeViewModelFactory(repository)
       // homeViewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)

        val heroesRecyclerView:RecyclerView = binding.characterRecyclerView
        heroesRecyclerView.layoutManager = GridLayoutManager(activity,2)
        heroesRecyclerView.adapter = adapter


        binding.searchCharacterView.setOnQueryTextListener(object  : android.widget.SearchView.OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    listener.characterFilteredByName(query)
                }else{
                    listener.characterWithOutFilter()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    listener.characterFilteredByName(newText)
                }else{
                    listener.characterWithOutFilter()
                }
                return false
            }
        })


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
        fun characterFilteredByName(name:String)
        fun characterWithOutFilter()
        fun onSuperHeroClick(idPosition:Int)
    }

}