package com.kwekboss.allnews.ui.newsfeed


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kwekboss.allnews.R
import com.kwekboss.allnews.model.Article
import com.kwekboss.allnews.model.MainViewModel
import com.kwekboss.allnews.recyclerview.NewsAdapter
import kotlinx.coroutines.launch


class NewsFeedFragment : Fragment(), NewsAdapter.NewsfeedInterface {
    private lateinit var adapter: NewsAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflating the layout
        return inflater.inflate(R.layout.fragment_newsfeed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progressBar)

        //Instantiating the viewModel
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // setting up the recyclerView
        val recyclerview = view.findViewById<RecyclerView>(R.id.news_recyclerview)
        adapter = NewsAdapter(this)
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = adapter

          fetchNewsData()

    }

    //Implementing onClickListener
    override fun openClickedArticle(article: Article) {
       val action = NewsFeedFragmentDirections.actionNavigationHomeToArticleFragment(article)
       findNavController().navigate(action)
    }

    override fun saveNewsArticle(newsArticle: Article) {
        mainViewModel.saveNews(newsArticle)
        Toast.makeText(requireContext(),R.string.news_saved, Toast.LENGTH_SHORT).show()
    }

    private fun fetchNewsData(){
        mainViewModel.newsData.observe(viewLifecycleOwner){
           lifecycleScope.launch {
               adapter.submitData(it)
           }
 // This will show a progress bar when news is loading or when new item is loaded again
              adapter.addLoadStateListener { loadStates ->
           if(loadStates.refresh is LoadState.Loading|| loadStates.append is LoadState.Loading){
                      progressBar.isVisible = true
             }
                  else{
                      progressBar.isVisible = false
                      val errorState = when{
                          loadStates.append is LoadState.Error-> loadStates.append as LoadState.Error
                          loadStates.prepend is LoadState.Error-> loadStates.prepend as LoadState.Error
                          loadStates.refresh is LoadState.Error-> loadStates.refresh as LoadState.Error
                          else-> null
                      }
                      errorState?.let {
                          Toast.makeText(requireContext(),R.string.no_Internet, Toast.LENGTH_LONG).show()
                      }
                  }
              }




        }


    }


}

