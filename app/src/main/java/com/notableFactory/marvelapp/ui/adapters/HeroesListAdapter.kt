package com.notableFactory.marvelapp.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.model.SuperHero
import com.squareup.picasso.Picasso
import java.io.Serializable


class HeroesListAdapter() : RecyclerView.Adapter<HeroesListAdapter.CharacterListViewHolder>(), Serializable {
    var heroes :MutableList<SuperHero> = emptyList<SuperHero>().toMutableList()

        private lateinit var context:Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        context = parent.context
        return CharacterListViewHolder(layoutInflater.inflate(R.layout.recyclerview_characters,parent,false))
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val list = heroes[position]
        holder.characterName.text = list.name
        val imageUrl = "${list.thumbnailUrl}/portrait_xlarge.${list.thumbnailExt}"
        Picasso.get().load(imageUrl).resize(100,100).into(holder.thumbnail)
        holder.cardCharacter.setOnClickListener{
        }
    }

    override fun getItemCount(): Int {
        return heroes.size
    }

    inner class CharacterListViewHolder(view:View): RecyclerView.ViewHolder(view){
        val characterName: TextView = view.findViewById(R.id.characterName)
        val thumbnail : ImageView = view.findViewById(R.id.characterImage)
        val cardCharacter : LinearLayout = view.findViewById(R.id.characterLinearLayout)
    }

    fun setData(characterList:List<SuperHero>) {
        heroes.clear()
        this.heroes.addAll(characterList)
        notifyDataSetChanged()
    }
}
