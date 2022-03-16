package com.kwekboss.allnews.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.kwekboss.allnews.R

class FavouriteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //instantiating the viewModel//
        val favouriteViewModel = ViewModelProvider(this)[FavouriteViewModel::class.java]

        // inflating the fragment layout//
        val view = layoutInflater.inflate(R.layout.fragment_favourite, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.saved_news_recyclerview)


        return view
    }
}