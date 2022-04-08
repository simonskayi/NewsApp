package com.kwekboss.allnews

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

import androidx.navigation.fragment.navArgs

class ArticleFragment : Fragment() {
    private val args by navArgs<ArticleFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Hooking Up Views
        val webView = view.findViewById<WebView>(R.id.web_View)

        //Creating a safe Args object
        val article = args.newsArticle

     // Handling WebViewLogics
           webView.apply {
               webViewClient = WebViewClient()
               settings.javaScriptEnabled = true
               loadUrl(article.url)
           }
       }



  }


