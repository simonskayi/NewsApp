package com.kwekboss.allnews.ui.search


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kwekboss.allnews.R
import com.kwekboss.allnews.model.Article
import kotlin.math.abs

class SearchAdapter(private val openArticle: OpenArticle) :RecyclerView.Adapter<SearchAdapter.ViewHolder>(){


    private val  diffCallBack =  object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.news_layout, parent, false)
        return ViewHolder(layout,openArticle)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(differ.currentList[position])
    }

    inner class ViewHolder(itemView: View, openArticle: OpenArticle) :
        RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
              openArticle.openArticle(differ.currentList[absoluteAdapterPosition])
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

    interface OpenArticle {
        fun openArticle(article: Article)
    }

}






