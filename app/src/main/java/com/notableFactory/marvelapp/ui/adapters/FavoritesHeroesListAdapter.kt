package com.notableFactory.marvelapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.model.SuperHero

class FavoritesHeroesListAdapter() : RecyclerView.Adapter<FavoritesHeroesListAdapter.FavoritesHeroesListViewHolder>() {
    private lateinit var context: Context

    val favoritesHeroesList:MutableList<SuperHero> = emptyList<SuperHero>().toMutableList()

    private lateinit var clickListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(favoriteHero: SuperHero)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        clickListener = listener
    }


    inner class FavoritesHeroesListViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val favoritesHeroName:TextView = view.findViewById(R.id.favoritesHeroName)
        val favoritesHeroImage: ImageView = view.findViewById(R.id.favoritesHeroImage)

        val  context : View = view
        init {
            view.setOnClickListener {
                clickListener.onItemClick(favoritesHeroesList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesHeroesListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        context = parent.context
        return FavoritesHeroesListViewHolder(layoutInflater.inflate(R.layout.recycledview_favorites_heroes,parent,false))
    }

    override fun onBindViewHolder(holder: FavoritesHeroesListViewHolder, position: Int) {
        val favoriteHero = favoritesHeroesList[position]
        holder.favoritesHeroName.text = favoriteHero.name
        val imageUrl = "${favoriteHero.thumbnailUrl}/portrait_xlarge.${favoriteHero.thumbnailExt}"

        holder.favoritesHeroImage.load(imageUrl)
    }

    override fun getItemCount(): Int {
        return favoritesHeroesList.size
    }

    fun setData( favoritesList:List<SuperHero>){
        favoritesHeroesList.clear()
        favoritesHeroesList.addAll(favoritesList)
        notifyDataSetChanged()
    }
}
