package com.notableFactory.marvelapp.ui.comic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.model.MarvelComic
import com.notableFactory.marvelapp.model.SuperHero
import com.notableFactory.marvelapp.ui.adapters.ComicCharactersAdapter

class ComicActivity : AppCompatActivity() {

    private lateinit var comic: MarvelComic
    private lateinit var adapter: ComicCharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic)

        comic = intent.getParcelableExtra<MarvelComic>("comic") as MarvelComic
        loadComicData()
    }

    private fun loadComicData() {
        val comicTitle = findViewById<TextView>(R.id.comicTitle)
        comicTitle.text = comic.title

        val comicIssueNumber = findViewById<TextView>(R.id.comicIssueNumber)
        comicIssueNumber.text = "#${comic.issueNumber}"

        val comicDescription = findViewById<TextView>(R.id.comic_description)
        comicDescription.text = comic.description

        val comicThumbnail = findViewById<ImageView>(R.id.comicImage)
        comicThumbnail.load("${comic.thumbnailUrlPath}/portrait_xlarge.${comic.thumbnailUrlExt}")

        val comicCharactersList = findViewById<RecyclerView>(R.id.comicCharactersRecyclerView)
        adapter = ComicCharactersAdapter(comic.characters)
        comicCharactersList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        comicCharactersList.adapter = adapter
    }
}