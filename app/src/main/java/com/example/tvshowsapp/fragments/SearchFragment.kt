package com.example.tvshowsapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvshowsapp.R
import com.example.tvshowsapp.adapter.SearchTvShowAdapter
import com.example.tvshowsapp.adapter.TvShowAdapter
import com.example.tvshowsapp.ui.DetailActivity
import com.example.tvshowsapp.utils.MyDialog
import com.example.tvshowsapp.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment:Fragment(R.layout.fragment_search) {
    private val viewModel: MyViewModel by viewModels()
    private lateinit var searchShowAdapter: SearchTvShowAdapter
    private lateinit var dialog: MyDialog
    private var job: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpDialog()
        setUpRv()



        et_search?.addTextChangedListener { et ->

            job?.cancel()
            job = lifecycleScope.launch(Dispatchers.Main) {
                delay(500)
                et?.let {
                    viewModel.searchAllTvShows(it.toString())
                }
            }

        }

        viewModel.searchTvShowResponse.observe(viewLifecycleOwner, Observer {searchTvShow->
            searchShowAdapter.items = searchTvShow
        })

        searchShowAdapter.setOnItemClickListener { item->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra("detail_search",item)
            startActivity(intent)

        }


    }

    private fun setUpRv() {

        searchShowAdapter = SearchTvShowAdapter()
        rv_search_shows.apply {

            adapter = searchShowAdapter
            layoutManager = GridLayoutManager(activity,2, GridLayoutManager.VERTICAL,false)
            setHasFixedSize(true)

        }

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