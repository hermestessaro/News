package com.application.news.view.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.application.news.R
import com.application.news.databinding.FragmentFeedBinding
import com.application.news.model.News
import com.application.news.view.adapters.NewsPagedListAdapter
import com.application.news.viewmodel.FeedViewModel

class FeedFragment: Fragment() {

    private lateinit var viewModel: FeedViewModel
    private lateinit var binding: FragmentFeedBinding
    private var normalNewsListAdapter = NewsPagedListAdapter()
    private var highlightNewsListAdapter = NewsPagedListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = FeedViewModel(requireActivity().application)

        binding.carouselList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = highlightNewsListAdapter
        }

        binding.newsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = normalNewsListAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.regularNewsLiveData.observe(viewLifecycleOwner, {
            normalNewsListAdapter.submitList(it)
        })
        viewModel.highlightedNewsLiveData.observe(viewLifecycleOwner, {
            highlightNewsListAdapter.submitList(it)
        })
    }

}