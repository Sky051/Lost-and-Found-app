package com.example.lostandfound

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imageuploader.imagesAdapter2
import com.example.lostandfound.databinding.ActivityFoundshowallBinding
import com.google.android.gms.common.util.Strings
import com.google.firebase.firestore.FirebaseFirestore


class foundshowall : AppCompatActivity() {

    private lateinit var binding:ActivityFoundshowallBinding
    private lateinit var firebaseFirestore: FirebaseFirestore
    private var mList = mutableListOf<String>()
    private lateinit var adapter: imagesAdapter2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFoundshowallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVars()
        getImages()
    }

    private fun initVars() {
        firebaseFirestore = FirebaseFirestore.getInstance()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = imagesAdapter2(mList)
        binding.recyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getImages(){
        binding.progressBar3.visibility = View.VISIBLE
        firebaseFirestore.collection("images")
            .get().addOnSuccessListener {
                for(i in it){
                    mList.add(i.data["pic"].toString())
                }
                adapter.notifyDataSetChanged()
                binding.progressBar3.visibility = View.GONE
            }
    }

}
