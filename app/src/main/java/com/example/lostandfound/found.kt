package com.example.lostandfound

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.lostandfound.databinding.FragmentFoundBinding

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class found : Fragment() {
    private var _binding: FragmentFoundBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFoundBinding.inflate(inflater, container, false)
        binding.floatingActionButton.setOnClickListener {
            activity?.let {
                val intent = Intent(it, Found_upload::class.java)
                it.startActivity(intent)
            }
        }


        return binding.root
        }

}