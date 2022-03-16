package com.kwekboss.allnews.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.kwekboss.allnews.R


class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // instantiating the viewModel's fragment//
        val moreViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        //inflating the fragment //
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.search_recyclerview)
        val searchbar = view.findViewById<SearchView>(R.id.searchView2)

        return view
    }
}