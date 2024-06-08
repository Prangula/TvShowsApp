package com.example.tvshowsapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvshowsapp.R
import com.example.tvshowsapp.models.modelepisode.EpisodeResponseItem
import com.example.tvshowsapp.models.modelshow.ShowResponseItem
import kotlinx.android.synthetic.main.item_cast.view.*
import kotlinx.android.synthetic.main.item_episodes.view.*

class ShowEpisodesAdapter:RecyclerView.Adapter<ShowEpisodesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View)
        :RecyclerView.ViewHolder(itemView)

    private val diffCallBack = object : DiffUtil.ItemCallback<EpisodeResponseItem>(){
        override fun areItemsTheSame(
            oldItem: EpisodeResponseItem,
            newItem: EpisodeResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: EpisodeResponseItem,
            newItem: EpisodeResponseItem
        ): Boolean {
            return newItem == oldItem
        }

    }

    private val differ = AsyncListDiffer(this,diffCallBack)
    var items:List<EpisodeResponseItem>
    get() = differ.currentList
    set(value) {
        differ.submitList(value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_episodes,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item = items[position]

        holder.itemView.apply {

            item_episodes_tv.text = item.name+" (Season"+" ${item.season},"+ " Episode"+ "${ item.number})"
            Glide
                .with(this)
                .load(item.image?.original)
                .into(item_episodes_iv)
        }
    }

}