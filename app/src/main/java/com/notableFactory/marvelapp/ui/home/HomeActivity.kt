package com.notableFactory.marvelapp.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.databinding.HomeActivityBinding
import com.notableFactory.marvelapp.model.SuperHero
import com.notableFactory.marvelapp.ui.Heroe.SuperHeroeActivity
import com.notableFactory.marvelapp.ui.adapters.HeroesListAdapter
import com.notableFactory.marvelapp.utils.addFragment
import com.notableFactory.marvelapp.utils.replaceFragment
import com.notableFactory.marvelapp.viewmodel.HomeViewModel
import kotlinx.coroutines.SupervisorJob

class HomeActivity : AppCompatActivity(), BrowseFragment.OnBrowseFragmentInteractionListener {

    //private val adapter = HeroesListAdapter()

    private lateinit var navController: NavController
    private lateinit var homeBinding: HomeActivityBinding
    private val fragmentManager = supportFragmentManager
    private val fragmentContainer = R.id.fragmentContainer
    private val viewModel: HomeViewModel = HomeViewModel()
    private lateinit var adapter: HeroesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)

        adapter = HeroesListAdapter()
        fragmentManager.addFragment(fragmentContainer, FavoritesFragment(), true, null)


        homeBinding.bottomNavigationView.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.favoritesFragmentItem -> fragmentManager.replaceFragment(
                    fragmentContainer,
                    FavoritesFragment(), false, null
                )
                R.id.browseFragmentItem -> {
                    fragmentManager.replaceFragment(
                        fragmentContainer,
                        BrowseFragment.newInstance(adapter), false, null
                    )


                }
                R.id.userFragmentItem -> fragmentManager.replaceFragment(
                    fragmentContainer,
                    UserFragment(), false, null
                )
                else -> {
                }
            }
            true
        }



        setObservers()

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    private fun setObservers() {
        viewModel.heroesList.observe(this) { superHeroList ->
            populateData(superHeroList)
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchCharacters()
    }

    fun loadAdapter() {
        val heroesRecyclerView: RecyclerView = findViewById(R.id.characterRecyclerView)
        heroesRecyclerView.layoutManager = GridLayoutManager(this, 2)
        heroesRecyclerView.adapter = adapter
    }

    private fun populateData(superHeroList: List<SuperHero>) {
        adapter.setData(superHeroList)
        adapter.notifyDataSetChanged()
    }

    override fun characterFilteredByName(name: String) {
        viewModel.searchByNameStartWith(name)
    }

    override fun characterWithOutFilter() {
        viewModel.fetchCharacters()
    }

    override fun onSuperHeroClick(heroe: SuperHero) {
        val myIntent = Intent(this, SuperHeroeActivity::class.java)
        this.startActivity(myIntent)
    }



}
/*
    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }*/

/*
        supportFragmentManager.addFragment(R.id.fragmentContainer,
            BrowseFragment(),
false,
            BrowseFragment.TAG
            )



        navController = Navigation.findNavController(this, R.id.fragmentContainer)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        bottomNavigationView.setupWithNavController(navController)

        NavigationUI.setupActionBarWithNavController(this,navController)
        val heroesRecyclerView:RecyclerView = findViewById(R.id.characterRecyclerView)
        heroesRecyclerView.layoutManager = GridLayoutManager(this,2)
        heroesRecyclerView.adapter = adapter

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.favoritesFragmentItem->{
                    supportFragmentManager.popBackStack()
                    supportFragmentManager.replaceFragment(
                        R.id.fragmentContainer,
                        FavoritesFragment(),
                        false,
                        FavoritesFragment.TAG
                    )
                    true
                }
                R.id.browseFragmentItem->{
                    supportFragmentManager.popBackStack()
                    supportFragmentManager.replaceFragment(
                        R.id.fragmentContainer,
                        BrowseFragment(),
                        false,
                        BrowseFragment.TAG
                    )
                    true
                }
                R.id.userFragmentItem-> {
                    supportFragmentManager.popBackStack()
                    supportFragmentManager.replaceFragment(
                        R.id.fragmentContainer,
                        UserFragment(),
                        false,
                        UserFragment.TAG
                    )
                    true
                }

                else -> {false}
            }

        }



        adapter.setOnItemClickListener(object : HeroesListAdapter.onItemClickListener {
            override fun onItemClick(heroe: SuperHero) {
                supportFragmentManager.addFragment
                val intent = Intent(this@HomeActivity, CreateOrEditNoteActivity::class.java)
                intent.putExtra("AccessMode", AccessMode.EDIT.mode)
                intent.putExtra("UserId", userId)
                intent.putExtra("LastNoteId", note.noteId)
                val bundle = Bundle()
                bundle.putSerializable("value", note)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })*/
