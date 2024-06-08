package com.example.tvshowsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvshowsapp.R
import com.example.tvshowsapp.modelcast.CastResponseItem
import kotlinx.android.synthetic.main.item_cast.view.*
import kotlinx.android.synthetic.main.item_show_search.view.*

class ShowCastAdapter:RecyclerView.Adapter<ShowCastAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View)
        :RecyclerView.ViewHolder(itemView)

    private val diffCallBack = object : DiffUtil.ItemCallback<CastResponseItem>(){
        override fun areItemsTheSame(
            oldItem: CastResponseItem,
            newItem:CastResponseItem
        ): Boolean {
            return oldItem.person!!.id == newItem.person!!.id
        }

        override fun areContentsTheSame(
            oldItem: CastResponseItem,
            newItem: CastResponseItem
        ): Boolean {
            return newItem == oldItem
        }

    }

    private val differ = AsyncListDiffer(this,diffCallBack)
    var items:List<CastResponseItem>
    get() = differ.currentList
    set(value) {
        differ.submitList(value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cast,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val item = items[position]

        holder.itemView.apply {

            item_cast_tv.text = item.person!!.name
            Glide
                .with(this)
                .load(item.person?.image?.original)
                .into(item_cast_iv)

        }
    }



}