package com.notableFactory.marvelapp.ui.comic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.notableFactory.marvelapp.R
import com.notableFactory.marvelapp.model.MarvelComic
import com.notableFactory.marvelapp.model.SuperHero

class ComicActivity : AppCompatActivity() {

    private lateinit var comic: MarvelComic

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
    }
}