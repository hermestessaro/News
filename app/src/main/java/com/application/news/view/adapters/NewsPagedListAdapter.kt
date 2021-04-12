package com.application.news.view.adapters

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.news.R
import com.application.news.databinding.ItemHighlightNewsBinding
import com.application.news.databinding.ItemNewsBinding
import com.application.news.model.News
import com.application.news.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.item_news.view.*

class NewsPagedListAdapter(val viewModel: FeedViewModel): PagedListAdapter<News, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<News>(){
    override fun areItemsTheSame(oldItem: News, newItem: News)
        = oldItem.title == oldItem.title

    override fun areContentsTheSame(oldItem: News, newItem: News)
        = oldItem == newItem
}), StarClickListener{

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
            holder.view.listener = this
            holder.view.position = position
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(getItem(position)?.highlight == true){
            NEWS_HIGHLITED
        } else {
            NEWS_REGULAR
        }
    }

    override fun onNewsStarClicked(v: View, position: Int) {
        getItem(position)?.let { news ->
            if(news.favorited){
                    viewModel.updateFavorite(false, news.title, v.context)
            } else {
                viewModel.updateFavorite(true, news.title, v.context)
            }
            notifyItemChanged(position)
        }

    }

    class NewsViewHolder(var view: ItemNewsBinding) : RecyclerView.ViewHolder(view.root)
    class HighlightedNewsViewHolder(var view: ItemHighlightNewsBinding) : RecyclerView.ViewHolder(view.root)
}