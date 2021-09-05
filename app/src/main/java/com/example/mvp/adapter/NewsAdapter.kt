package com.example.mvp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvp.R
import com.example.mvp.model.Article
import kotlinx.android.synthetic.main.news_row.view.*

class NewsAdapter(val listener:OnItemClickListener):RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    private val diffUtil=object: DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }

    }
    val differ=AsyncListDiffer(this,diffUtil)

    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_row,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val a=differ.currentList[position]

        holder.itemView.apply {
            text_title.text=a.title

            setOnClickListener {
                listener.onItemClickListener(a)
            }
        }
        Glide.with(holder.itemView.imageView)
            .load(a.urlToImage).into(holder.itemView.imageView)


    }

    interface OnItemClickListener{
        fun onItemClickListener(article: Article)
    }

    override fun getItemCount(): Int =differ.currentList.size
}