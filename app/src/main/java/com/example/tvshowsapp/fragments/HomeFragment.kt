package com.example.tvshowsapp.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvshowsapp.R
import com.example.tvshowsapp.adapter.TvShowAdapter
import com.example.tvshowsapp.ui.DetailActivity
import com.example.tvshowsapp.utils.MyDialog
import com.example.tvshowsapp.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment:Fragment(R.layout.fragment_home) {

    private val viewModel: MyViewModel by viewModels()
    private lateinit var tvShowAdapter: TvShowAdapter
    private lateinit var dialog: MyDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpDialog()
        setUpShowsRv()

        tvShowAdapter.setOnItemClickListener { item->
           val intent = Intent(activity,DetailActivity::class.java)
            intent.putExtra("detail",item)
            startActivity(intent)

        }

    }
    private fun setUpShowsRv() {

        tvShowAdapter = TvShowAdapter()
        rv_shows.apply {

            adapter = tvShowAdapter
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
        }

        viewModel.tvShowResponse.observe(viewLifecycleOwner, Observer { tvShow ->

            tvShowAdapter.items = tvShow

        })

    }
    private fun setUpDialog(){

        dialog = MyDialog()
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading->

            if(isLoading){

                dialog.showDialog(activity!!)

            }else{

                dialog.hideDialog()
            }

        })


    }
}