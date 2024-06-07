package com.example.tvshowsapp.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.bumptech.glide.Glide
import com.example.tvshowsapp.R
import com.example.tvshowsapp.modelsearch.SearchShowResponseItem
import com.example.tvshowsapp.modelshow.TvShowResponseItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.*

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var item: TvShowResponseItem
    private lateinit var itemSearch: SearchShowResponseItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if(intent.hasExtra("detail")){
            item = intent.getParcelableExtra("detail")!!
            detailUi()
        }

       else if(intent.hasExtra("detail_search")){
            itemSearch= intent.getParcelableExtra("detail_search")!!
            detailUiSearch()
        }




    }


    @SuppressLint("SetTextI18n")
    private fun detailUi() {
        Glide
            .with(this@DetailActivity)
            .load(item.image?.original ?: R.drawable.image)
            .into(detail_iv)

        detail_duration.text = "${item.averageRuntime ?: "N/A"}m"
        detail_imdb.text = item.officialSite ?: "N/A"
        detail_title.text = item.name ?: "N/A"
        detail_genres.text = item.genres?.joinToString(", ") ?: "N/A"
        detail_premiered.text = item.premiered ?: "N/A"
        detail_language.text = item.language ?: "N/A"
        // Strip HTML tags from the summary
        val summaryWithoutHtml = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(item.summary ?: "", Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            Html.fromHtml(item.summary ?: "").toString()
        }
        detail_overview.text = summaryWithoutHtml ?: "N/A"
    }

    @SuppressLint("SetTextI18n")
    private fun detailUiSearch() {
        Glide
            .with(this@DetailActivity)
            .load(itemSearch.show?.image?.original ?: R.drawable.image)
            .into(detail_iv)

        detail_duration.text = "${itemSearch.show?.averageRuntime ?: "N/A"}m"
        detail_imdb.text = itemSearch.show?.officialSite ?: "N/A"
        detail_title.text = itemSearch.show?.name ?: "N/A"
        detail_genres.text = itemSearch.show?.genres?.joinToString(", ") ?: "N/A"
        detail_premiered.text = itemSearch.show?.premiered ?: "N/A"
        detail_language.text = itemSearch.show?.language ?: "N/A"
        // Strip HTML tags from the summary
        val summaryWithoutHtml = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(itemSearch.show?.summary ?: "", Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            Html.fromHtml(itemSearch.show?.summary ?: "").toString()
        }
        detail_overview.text = summaryWithoutHtml ?: "N/A"
    }

}