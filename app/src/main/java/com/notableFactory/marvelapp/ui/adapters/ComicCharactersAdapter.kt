package com.notableFactory.marvelapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.notableFactory.marvelapp.R

class ComicCharactersAdapter(private val characters: List<String>) : RecyclerView.Adapter<ComicCharactersAdapter.ComicCharacterListViewHolder>() {

    class ComicCharacterListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val characterName: TextView = view.findViewById(R.id.comicCharacterName)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComicCharacterListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ComicCharacterListViewHolder(layoutInflater.inflate(R.layout.recyclerview_comic_character,parent,false))
    }

    override fun onBindViewHolder(holder: ComicCharacterListViewHolder, position: Int) {
        holder.characterName.text = characters[position]
    }

    override fun getItemCount(): Int {
        return characters.size
    }
}