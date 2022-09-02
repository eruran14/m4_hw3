package com.eru.les3_m4

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eru.les3_m4.databinding.NewsItemBinding
import com.eru.les3_m4.models.News

class NewsAdapter(var context: Context, private val list: List<News>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: NewsItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int){
            binding.newsTitle.text = list[position].title
            binding.newsDate.text = list[position].createdAt.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = list.size
}