package com.kwekboss.allnews.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kwekboss.allnews.R
import com.kwekboss.allnews.model.Article
import com.kwekboss.allnews.recyclerview.FavouriteAdapter
import com.kwekboss.allnews.model.MainViewModel

class FavouriteFragment : Fragment(), FavouriteAdapter.FavouriteNewsInterface {
    lateinit var favouriteViewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

      favouriteViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val view = layoutInflater.inflate(R.layout.fragment_favourite, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.saved_news_recyclerview)
        val adapter = FavouriteAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        favouriteViewModel.getSavedNews.observe(viewLifecycleOwner){
            adapter.differ.submitList(it)
        }
        return view
    }

    override fun openSavedArticle(news: Article) {
       val action = FavouriteFragmentDirections.actionNavigationFavouriteToArticleFragment(news)
        findNavController().navigate(action)

    }

    override fun deleteSavedArticle(news: Article) {
        favouriteViewModel.deleteNews(news)
        Toast.makeText(requireContext(), R.string.news_deleted, Toast.LENGTH_SHORT).show()
    }
}