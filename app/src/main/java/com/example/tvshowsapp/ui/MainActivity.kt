package com.example.tvshowsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvshowsapp.R
import com.example.tvshowsapp.adapter.TvShowAdapter
import com.example.tvshowsapp.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MyViewModel by viewModels()
    private lateinit var tvShowAdapter: TvShowAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       setUpRv()
    }

    private fun setUpRv(){

        tvShowAdapter = TvShowAdapter()
        rv_shows.apply {

            adapter = tvShowAdapter
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
        }

        viewModel.tvShowResponse.observe(this@MainActivity, Observer { tvShow->

            tvShowAdapter.items = tvShow

        })

    }
}