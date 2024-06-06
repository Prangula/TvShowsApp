package com.example.tvshowsapp.ui

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvshowsapp.R
import com.example.tvshowsapp.adapter.TvShowAdapter
import com.example.tvshowsapp.utils.MyDialog
import com.example.tvshowsapp.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
open class MainActivity : AppCompatActivity() {

    private val viewModel: MyViewModel by viewModels()
    private lateinit var tvShowAdapter: TvShowAdapter
    private lateinit var dialog:MyDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpDialog()
        setUpRv()

        tvShowAdapter.setOnItemClickListener {item->

        val intent = Intent(this@MainActivity,DetailActivity::class.java)
        intent.putExtra("detail",item)
        startActivity(intent)



        }
    }

    private fun setUpRv() {

        tvShowAdapter = TvShowAdapter()
        rv_shows.apply {

            adapter = tvShowAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

        viewModel.tvShowResponse.observe(this@MainActivity, Observer { tvShow ->

            tvShowAdapter.items = tvShow

        })

    }

    private fun setUpDialog(){

        dialog = MyDialog()
        viewModel.isLoading.observe(this@MainActivity, Observer { isLoading->

            if(isLoading){

                dialog.showDialog(this@MainActivity)

            }else{

                dialog.hideDialog()
            }

        })


    }
}