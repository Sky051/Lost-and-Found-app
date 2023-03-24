package com.example.lostandfound

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lostandfound.databinding.FragmentFoundBinding
import com.example.lostandfound.databinding.FragmentLostBinding


class lost: Fragment() {
    private var _binding: FragmentLostBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLostBinding.inflate(inflater, container, false)
        binding.floatingActionButton.setOnClickListener {
            activity?.let {
                val intent = Intent(it, lostupload::class.java)
                it.startActivity(intent)
            }
        }


        return binding.root
    }

}
