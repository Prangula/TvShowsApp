package com.example.tvshowsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvshowsapp.R
import com.example.tvshowsapp.models.modelsearch.SearchShowResponseItem
import kotlinx.android.synthetic.main.item_show_search.view.*

class SearchTvShowAdapter:RecyclerView.Adapter<SearchTvShowAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View)
        :RecyclerView.ViewHolder(itemView)

    private val diffCallBack = object : DiffUtil.ItemCallback<SearchShowResponseItem>(){
        override fun areItemsTheSame(
            oldItem: SearchShowResponseItem,
            newItem: SearchShowResponseItem
        ): Boolean {
            return oldItem.show?.id == newItem.show?.id
        }

        override fun areContentsTheSame(
            oldItem: SearchShowResponseItem,
            newItem: SearchShowResponseItem
        ): Boolean {
            return newItem == oldItem
        }

    }

    private val differ = AsyncListDiffer(this,diffCallBack)
    var items:List<SearchShowResponseItem>
    get() = differ.currentList
    set(value) {
        differ.submitList(value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_show_search,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item = items[position]

        holder.itemView.apply {

            item_tv.text = item.show?.name
            Glide
                .with(this)
                .load(item.show?.image?.original)
                .into(item_iv)

            setOnClickListener {
                onItemClickListener!!.let {
                    it(item)
                }
            }

        }
    }

    private var onItemClickListener:((SearchShowResponseItem)->Unit)? = null


    fun setOnItemClickListener(listener:(SearchShowResponseItem)->Unit) {

        onItemClickListener = listener

    }

}