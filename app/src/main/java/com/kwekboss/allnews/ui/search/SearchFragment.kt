package com.kwekboss.allnews.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kwekboss.allnews.R
import com.kwekboss.allnews.model.Article
import com.kwekboss.allnews.ui.newsfeed.MainViewModel


class SearchFragment : Fragment(), SearchAdapter.OpenArticle {
    private lateinit var adapter: SearchAdapter
    lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_search, container, false)
    }
    //

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.search_recyclerview)
        val searchbar = view.findViewById<SearchView>(R.id.searchView2)


        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        //searchbar codes
        searchbar?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(searchQuery: String?): Boolean {
                if (searchQuery != null) {
                   viewModel.getSearch(searchQuery)

                }
                return false
            }
            override fun onQueryTextChange(string: String?): Boolean {

                return false
            }
        })

        adapter = SearchAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        viewModel.searchResponse.observe(viewLifecycleOwner) {
            adapter.differ.submitList(it)
        }

    }

    override fun openArticle(article: Article) {
        val action = SearchFragmentDirections.actionNavigationSearchToArticleFragment(article)
        findNavController().navigate(action)
    }


}