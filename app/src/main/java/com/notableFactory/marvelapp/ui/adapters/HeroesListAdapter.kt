package com.notableFactory.marvelapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.model.SuperHero

class HeroesListAdapter: RecyclerView.Adapter<HeroesListAdapter.SuperHeroViewHolder>() {

    var heroes: List<SuperHero> = emptyList()

    class SuperHeroViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var characterItemTitle: TextView
        var characterItemSummary: TextView

        init {
            characterItemTitle = itemView.findViewById(R.id.hero_name)
            characterItemSummary = itemView.findViewById(R.id.hero_description)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesListAdapter.SuperHeroViewHolder {
        return SuperHeroViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.character_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: HeroesListAdapter.SuperHeroViewHolder, position: Int) {
        val listItem = heroes[position]

        holder.characterItemTitle.text = listItem.name
        holder.characterItemSummary.text = listItem.description
    }

    override fun getItemCount(): Int {
        return heroes.size
    }
}