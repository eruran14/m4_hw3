package com.eru.les3_m4.ui

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.eru.les3_m4.App
import com.eru.les3_m4.databinding.FragmentNewsBinding
import com.eru.les3_m4.models.News
import java.util.*

class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            save()
        }
    }

    private fun save() {
        val title = binding.editText1.text.toString().trim()
        val news = News(0,title, getDate(System.currentTimeMillis(), "hh:mm dd-MMMM-yyyy"))

        App.dataBase.newsDao().insert(news)
        findNavController().navigateUp()
    }

    private fun getDate(milliSeconds: Long, dateFormat: String): String? {
        val formatter = SimpleDateFormat(dateFormat)
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }


}