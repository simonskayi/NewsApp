package com.kwekboss.allnews.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kwekboss.allnews.R
import com.kwekboss.allnews.model.Article
import com.kwekboss.allnews.recyclerview.NewsAdapter

class HomeFragment : Fragment(), NewsAdapter.ArticleClicked {
    private lateinit var adapter: NewsAdapter
    private lateinit var progressBar: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflating the layout
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progressBar)
        //Instantiating the viewModel
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        // setting up the recyclerView
        val recyclerview = view.findViewById<RecyclerView>(R.id.news_recyclerview)
        adapter = NewsAdapter(this)
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = adapter
        // Setting up the progressBar
        progressBar.isVisible = true

        // Calling the Api getNews Function
        homeViewModel.loadData()

        homeViewModel.getResponse.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.differ.submitList(it.articles)
                progressBar.isVisible = false
            } else {
                Toast.makeText(activity, "An unexpected error Occurred", Toast.LENGTH_SHORT).show()
            }
        }

    }

    //Implementing onClickListener
    override fun openClickedArticle(article: Article) {
       // Toast.makeText(activity, "Will implement u soon", Toast.LENGTH_SHORT).show()
    val action = HomeFragmentDirections.actionNavigationHomeToArticleFragment(article)
        findNavController().navigate(action)
    }

}

