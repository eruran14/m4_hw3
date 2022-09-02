package com.eru.les3_m4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.eru.les3_m4.databinding.FragmentBoardBinding
import com.eru.les3_m4.ui.Prefs
import me.relex.circleindicator.CircleIndicator3

class BoardFragment : Fragment() {

    private lateinit var binding: FragmentBoardBinding
    private val data = arrayListOf<Boards>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBoardBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData(data)

        val adapter = BoardAdapter(requireContext(),findNavController(), data)
        binding.viewPager.adapter = adapter

        val indicator: CircleIndicator3 = binding.indicator
        indicator.setViewPager(binding.viewPager)

        binding.btnSkip.setOnClickListener {
            val prefs = Prefs(requireContext())
            prefs.saveState()
            findNavController().navigateUp()
        }


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }

        })
    }

    private fun loadData(data: ArrayList<Boards>) {
        data.add(Boards("Hello", "Welcome to this app", R.drawable.hello_stitch))
        data.add(Boards("Liverpool FC", "This is the best football club in the world", R.drawable.liverpool))
        data.add(Boards("Mo Salah", "And this is the best player in the world, which plays for Liverpool FC", R.drawable.salah))
        data.add(Boards("Manchester United", "Liverpool FC's historical rivals", R.drawable.manchester_united))
    }


}