package com.eru.les3_m4.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.eru.les3_m4.App
import com.eru.les3_m4.NewsAdapter
import com.eru.les3_m4.R
import com.eru.les3_m4.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null


    private val binding get() = _binding!!
    private lateinit var adapter: NewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
           findNavController().navigate(R.id.newsFragment)
        }
        App.dataBase.newsDao().getAllLive().observe(viewLifecycleOwner){ list ->
            adapter.addItems(list)
//            if(list.isNotEmpty()){
//                list.sortedBy { it.createdAt  }
//            }
            for (it in list){

            }
        }
        adapter = NewsAdapter{
            val alertDialogBuilder = AlertDialog.Builder(requireContext())
            alertDialogBuilder.setTitle("Вы действительно хотите удалить элемент из списка?")
            alertDialogBuilder.setMessage("Вы собираетесь удалить один из элементов из базы данных в списке")
            alertDialogBuilder.setPositiveButton("Да"){ _, _ ->
                App.dataBase.newsDao().delete(adapter.getItem(it))
            }
            alertDialogBuilder.setNegativeButton("Нет"){_,_ ->
                    Toast.makeText(context, "ну ладно", Toast.LENGTH_SHORT).show()
                }
            alertDialogBuilder.show()

        }


            binding.newsRecycler.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}