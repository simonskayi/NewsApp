package com.kwekboss.allnews.recyclerview


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kwekboss.allnews.R
import com.kwekboss.allnews.model.Article

class NewsAdapter(private val newsfeedInterface: NewsfeedInterface) :
   PagingDataAdapter<Article,NewsAdapter.ViewHolder>(DiffCallBack) {

    // Implementing DiffUtils
    object DiffCallBack : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.news_layout, parent, false)
        return ViewHolder(layout,newsfeedInterface)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = getItem(position)
        if (article != null) {
            holder.bindView(article)
        }
    }

    inner class ViewHolder(itemView: View, newsArticle: NewsfeedInterface) :
        RecyclerView.ViewHolder(itemView) {
        private val saveArticle = itemView.findViewById<ImageView>(R.id.save_news_article)

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let { article ->
                    newsArticle.openClickedArticle(article)
                }
            }

            saveArticle.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let {
                    newsArticle.saveNewsArticle(it)
                }
            }
        }

        fun bindView(news: Article) {
            val newsTittle = itemView.findViewById<TextView>(R.id.news_tittle)
            val newsImage = itemView.findViewById<ImageView>(R.id.news_image)
            val newsSource = itemView.findViewById<TextView>(R.id.news_category)
            val newsDescription = itemView.findViewById<TextView>(R.id.news_description)

            newsTittle.text = news.title
            Glide.with(itemView.context).load(news.urlToImage)
                .placeholder(R.drawable.placeholder_image)
                .centerCrop()
                .into(newsImage)
            newsSource.text = news.source.name
            newsDescription.text = news.description
        }
    }

    interface NewsfeedInterface {
        fun openClickedArticle(article: Article)
        fun saveNewsArticle(newsArticle:Article)
    }

}
