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
import com.notableFactory.marvelapp.model.MarvelComic

class ComicListAdapter() : RecyclerView.Adapter<ComicListAdapter.ComicsListViewHolder>() {
    private lateinit var context: Context

    val comics:MutableList<MarvelComic> = emptyList<MarvelComic>().toMutableList()

    private lateinit var clickListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(comic: MarvelComic)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        clickListener = listener
    }


    inner class ComicsListViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val comicName:TextView = view.findViewById(R.id.comicRecycledName)
        val comicImage: ImageView = view.findViewById(R.id.comicRecycledImage)

        val  context : View = view
        init {
            view.setOnClickListener {
                clickListener.onItemClick(comics[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        context = parent.context
        return ComicsListViewHolder(layoutInflater.inflate(R.layout.recyclerview_comics,parent,false))
    }

    override fun onBindViewHolder(holder: ComicsListViewHolder, position: Int) {
        val comic = comics[position]
        holder.comicName.text = comic.title
        val imageUrl = "${comic.thumbnailUrlPath}/portrait_xlarge${comic.thumbnailUrlExt}"

        holder.comicImage.load(imageUrl)
    }

    override fun getItemCount(): Int {
        return comics.size
    }

    fun setData( comicsList:List<MarvelComic>){
        comics.clear()
        comics.addAll(comicsList)
        notifyDataSetChanged()
    }
}