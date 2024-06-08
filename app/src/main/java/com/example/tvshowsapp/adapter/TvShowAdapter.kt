package com.example.tvshowsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvshowsapp.R
import com.example.tvshowsapp.models.modelshow.ShowResponseItem
import kotlinx.android.synthetic.main.item_show_search.view.*

class TvShowAdapter:RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View)
        :RecyclerView.ViewHolder(itemView)

    private val diffCallBack = object : DiffUtil.ItemCallback<ShowResponseItem>(){
        override fun areItemsTheSame(
            oldItem: ShowResponseItem,
            newItem: ShowResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ShowResponseItem,
            newItem: ShowResponseItem
        ): Boolean {
            return newItem == oldItem
        }

    }

    private val differ = AsyncListDiffer(this,diffCallBack)
    var items:List<ShowResponseItem>
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

            item_tv.text = item.name
            Glide
                .with(this)
                .load(item.image?.original)
                .into(item_iv)

            setOnClickListener {
                onItemClickListener!!.let {
                    it(item)
                }
            }

        }
    }

    private var onItemClickListener:((ShowResponseItem)->Unit)? = null


    fun setOnItemClickListener(listener:(ShowResponseItem)->Unit) {

        onItemClickListener = listener

    }

}