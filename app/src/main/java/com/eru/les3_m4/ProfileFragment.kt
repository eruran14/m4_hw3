package com.eru.les3_m4

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.eru.les3_m4.databinding.FragmentProfileBinding
import com.eru.les3_m4.ui.Prefs


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private var selectedImage: Uri? = null
    private lateinit var prefs: Prefs
    private val mContent =
        registerForActivityResult(
            ActivityResultContracts.GetContent()
        ) {
            binding.ivImage.setImageURI(it)
            prefs.saveImage("image", it)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prefs = Prefs(requireContext())

//        val uri = prefs.getImage()
//        binding.ivImage.setImageURI(uri)

        binding.ivImage.setOnClickListener {
            mContent.launch("image/*")

        }

//        val prefs = Prefs(requireContext())
//
//        val encodedImage = prefs.isChosen("image")
//
//        if (!encodedImage.equals("")){
//            requireActivity().grantUriPermission(requireContext().packageName, Uri.parse(encodedImage), Intent.FLAG_GRANT_READ_URI_PERMISSION)
//            val takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION
//            if (encodedImage != null) {
//                requireActivity().contentResolver.takePersistableUriPermission(Uri.parse(encodedImage), takeFlags)
//            }
//            binding.ivImage.setImageURI(Uri.parse(encodedImage))
//        }
//
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(resultCode == Activity.RESULT_OK && data != null){
//            selectedImage = data.data
//            binding.ivImage.setImageURI(selectedImage)

        }
    }