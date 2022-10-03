package com.kwekboss.allnews

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar


import androidx.navigation.fragment.navArgs

class ArticleFragment : Fragment() {
    private val args by navArgs<ArticleFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val webView = view.findViewById<WebView>(R.id.web_View)
         val articleProgress = view.findViewById<ProgressBar>(R.id.articleProgress)
        // Get safe Args article
        val article = args.newsArticle
           webView.apply {
               settings.javaScriptEnabled = true
               webViewClient = object: WebViewClient(){
                   override fun onPageFinished(view: WebView?, url: String?) {
                       super.onPageFinished(view, url)
                       articleProgress.visibility = View.GONE
                   }

               }
                   loadUrl(article.url)
           }
       }



  }


