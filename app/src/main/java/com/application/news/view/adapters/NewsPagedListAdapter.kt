package com.application.news.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.news.R
import com.application.news.databinding.ItemNewsBinding
import com.application.news.model.News

class NewsPagedListAdapter(): PagedListAdapter<News, NewsPagedListAdapter.NewsViewHolder>(object : DiffUtil.ItemCallback<News>(){
    override fun areItemsTheSame(oldItem: News, newItem: News)
        = oldItem.title == oldItem.title

    override fun areContentsTheSame(oldItem: News, newItem: News)
        = oldItem == newItem
}){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemNewsBinding>(inflater, R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = getItem(position)
        item?.let{
            holder.view.news = item
        }
    }

    class NewsViewHolder(var view: ItemNewsBinding) : RecyclerView.ViewHolder(view.root)
}