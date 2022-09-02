package com.eru.les3_m4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.eru.les3_m4.databinding.BoardItemBinding
import com.eru.les3_m4.ui.Prefs

class BoardAdapter(var context: Context, var findNavController: NavController, private val data: ArrayList<Boards>) : RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: BoardItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int){
            binding.tvTitle.text = data[position].title
            binding.tvDescription.text = data[position].description
            binding.imageView.setImageResource(data[position].image)
            binding.btnStart.setOnClickListener {
                val prefs = Prefs(context)
                prefs.saveState()
                findNavController.navigateUp()
            }

            if (position == data.lastIndex){
                binding.btnStart.visibility = View.VISIBLE
            } else{
                binding.btnStart.visibility = View.INVISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(BoardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = data.size
}