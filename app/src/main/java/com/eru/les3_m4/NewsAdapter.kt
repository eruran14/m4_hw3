package com.eru.les3_m4


import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eru.les3_m4.databinding.NewsItemBinding
import com.eru.les3_m4.models.News
import java.util.*

class NewsAdapter(private val onClick: (pos: Int) -> Unit) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val list = arrayListOf<News>()
    private var currentAdapterPosition: Int = 0
    var onLongClick: ((pos:Int) -> Unit)? = null

    inner class ViewHolder(private val binding: NewsItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int){
            binding.newsTitle.text = list[position].title
            binding.newsDate.text = getDate(list[position].createdAt, "hh:mm dd-MMMM-yyyy")
            binding.root.setOnClickListener {
                onClick(adapterPosition)
                currentAdapterPosition = adapterPosition
            }
            binding.root.setOnLongClickListener {
                onLongClick?.invoke(adapterPosition)
                return@setOnLongClickListener true
            }



        }
        private fun getDate(milliSeconds: Long, dateFormat: String): String? {
            val formatter = SimpleDateFormat(dateFormat)
            val calendar: Calendar = Calendar.getInstance()
            calendar.timeInMillis = milliSeconds
            return formatter.format(calendar.time)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)

    }

    override fun getItemCount(): Int = list.size

    fun addItems(list: List<News>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()

    }

    fun getItem(i: Int) = list[currentAdapterPosition]


}