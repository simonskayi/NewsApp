package com.kwekboss.allnews.ui.search

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kwekboss.allnews.R
import com.kwekboss.allnews.model.Article
import com.kwekboss.allnews.recyclerview.SearchAdapter
import com.kwekboss.allnews.model.MainViewModel


class SearchFragment : Fragment(), SearchAdapter.SearchNewsInterface {
    private lateinit var adapter: SearchAdapter
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.search_recyclerview)
        val searchbar = view.findViewById<SearchView>(R.id.searchView2)

        adapter = SearchAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        //searchbar impl
        searchbar?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(searchQuery: String?): Boolean {
                if (searchQuery != null && hasInternetConnection()) {

                    viewModel.getSearch(searchQuery)
                }else{

                Snackbar.make(view,R.string.no_Internet,Snackbar.LENGTH_SHORT).show()

                }

                return false
            }

            override fun onQueryTextChange(string: String?): Boolean {

                return false
            }
        })

        viewModel.searchResponse.observe(viewLifecycleOwner) {
            adapter.differ.submitList(it)
        }

    }

    override fun openArticle(article: Article) {
        val action = SearchFragmentDirections.actionNavigationSearchToArticleFragment(article)
        findNavController().navigate(action)
    }

    fun hasInternetConnection():Boolean{
     val connectivityManager = activity?.
     getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
     val netCapability = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    return (netCapability!=null && netCapability.hasCapability(NET_CAPABILITY_INTERNET))

        }
}