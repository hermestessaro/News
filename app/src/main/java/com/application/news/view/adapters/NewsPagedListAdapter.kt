package com.application.news.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.news.R
import com.application.news.databinding.ItemHighlightNewsBinding
import com.application.news.databinding.ItemNewsBinding
import com.application.news.model.News

class NewsPagedListAdapter: PagedListAdapter<News, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<News>(){
    override fun areItemsTheSame(oldItem: News, newItem: News)
        = oldItem.title == oldItem.title

    override fun areContentsTheSame(oldItem: News, newItem: News)
        = oldItem == newItem
}){

    companion object {
        const val NEWS_HIGHLITED = 1
        const val NEWS_REGULAR = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if(viewType == NEWS_HIGHLITED){
            val view = DataBindingUtil.inflate<ItemHighlightNewsBinding>(inflater, R.layout.item_highlight_news, parent, false)
            HighlightedNewsViewHolder(view)
        } else {
            val view = DataBindingUtil.inflate<ItemNewsBinding>(inflater, R.layout.item_news, parent, false)
            NewsViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if(item?.highlight == true){
            (holder as HighlightedNewsViewHolder).view.news = item
        } else {
            (holder as NewsViewHolder).view.news = item
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(getItem(position)?.highlight == true){
            NEWS_HIGHLITED
        } else {
            NEWS_REGULAR
        }
    }

    class NewsViewHolder(var view: ItemNewsBinding) : RecyclerView.ViewHolder(view.root)
    class HighlightedNewsViewHolder(var view: ItemHighlightNewsBinding) : RecyclerView.ViewHolder(view.root)
}